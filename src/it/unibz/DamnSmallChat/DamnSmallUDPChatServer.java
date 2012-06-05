package it.unibz.DamnSmallChat;

import java.net.*;
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


	public DamnSmallUDPChatServer(String myAddress, String myPort, Channel currentChannel,PrintService log){
		mDatagramSocket = null;
		try{
			mAddress = InetAddress.getByName(myAddress);
			mPort = Integer.parseInt(myPort);
			mDatagramSocket = new DatagramSocket(mPort,mAddress);
			mReceiveBuffer = new byte[BUF_SIZE];
			mDatagramPacket = new DatagramPacket(mReceiveBuffer, BUF_SIZE);
			mLog = log;
		}catch(Exception e){

		}
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
