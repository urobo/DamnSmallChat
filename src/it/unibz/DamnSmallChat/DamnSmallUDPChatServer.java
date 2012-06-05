package it.unibz.DamnSmallChat;

import java.net.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.Thread;

public class DamnSmallUDPChatServer extends Thread {
	
	private static int BUF_SIZE = 2048;
	
	private InetAddress mAddress;
	private int mPort;
	private DatagramSocket mDatagramSocket;
	private DatagramPacket mDatagramPacket;
	private PrintService mLog;
	private byte[] mReceiveBuffer;
	private Channel mCurrentChannel;


	public DamnSmallUDPChatServer(String myAddress, String myPort, Channel currentChannel){
		mDatagramSocket = null;
		try{
			mAddress = InetAddress.getByName(myAddress);
			mPort = Integer.parseInt(myPort);
			mDatagramSocket = new DatagramSocket(mPort,mAddress);
			mReceiveBuffer = new byte[BUF_SIZE];
			mDatagramPacket = new DatagramPacket(mReceiveBuffer, BUF_SIZE);
			PrintWriter serverLogWriter = new PrintWriter(new FileWriter("server.log"));			
			mLog = new PrintService("Server", this.mDatagramSocket.getInetAddress(), this.mDatagramSocket.getLocalPort(), serverLogWriter);
		}catch(Exception e){

		}
	}
	
	/**
	 * @return the mAddress
	 */
	public InetAddress getmAddress() {
		return mAddress;
	}

	/**
	 * @param mAddress the mAddress to set
	 */
	public void setmAddress(InetAddress mAddress) {
		this.mAddress = mAddress;
	}

	/**
	 * @return the mPort
	 */
	public int getmPort() {
		return mPort;
	}

	/**
	 * @param mPort the mPort to set
	 */
	public void setmPort(int mPort) {
		this.mPort = mPort;
	}

	/**
	 * @return the mDatagramSocket
	 */
	public DatagramSocket getmDatagramSocket() {
		return mDatagramSocket;
	}

	/**
	 * @param mDatagramSocket the mDatagramSocket to set
	 */
	public void setmDatagramSocket(DatagramSocket mDatagramSocket) {
		this.mDatagramSocket = mDatagramSocket;
	}

	/**
	 * @return the mDatagramPacket
	 */
	public DatagramPacket getmDatagramPacket() {
		return mDatagramPacket;
	}

	/**
	 * @param mDatagramPacket the mDatagramPacket to set
	 */
	public void setmDatagramPacket(DatagramPacket mDatagramPacket) {
		this.mDatagramPacket = mDatagramPacket;
	}

	/**
	 * @return the mLog
	 */
	public PrintService getmLog() {
		return mLog;
	}

	/**
	 * @param mLog the mLog to set
	 */
	public void setmLog(PrintService mLog) {
		this.mLog = mLog;
	}

	/**
	 * @return the mReceiveBuffer
	 */
	public byte[] getmReceiveBuffer() {
		return mReceiveBuffer;
	}

	/**
	 * @param mReceiveBuffer the mReceiveBuffer to set
	 */
	public void setmReceiveBuffer(byte[] mReceiveBuffer) {
		this.mReceiveBuffer = mReceiveBuffer;
	}

	/**
	 * @return the mCurrentChannel
	 */
	public Channel getmCurrentChannel() {
		return mCurrentChannel;
	}

	/**
	 * @param mCurrentChannel the mCurrentChannel to set
	 */
	public void setmCurrentChannel(Channel mCurrentChannel) {
		this.mCurrentChannel = mCurrentChannel;
	}

	public void run(){
		try{
			while(true){
				mDatagramSocket.receive(mDatagramPacket);
				mLog.print("Server received message from ( "+ mDatagramPacket.getAddress() + "," + mDatagramPacket.getPort() + ") of length " + mDatagramPacket.getLength());
				mCurrentChannel.print(new String(mDatagramPacket.getData()));
			}
		}catch (Exception e){
				e.printStackTrace();
		}finally{
			if(mDatagramSocket!=null)
				mDatagramSocket.close();
		}
	}	
}
