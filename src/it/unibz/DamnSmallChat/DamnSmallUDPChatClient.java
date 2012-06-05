package it.unibz.DamnSmallChat;

import java.net.*;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.Thread;

public class DamnSmallUDPChatClient extends Thread {

	private static int TIME_OUT = 5000;

	private InetAddress mServerAddress;
	private int mServerPort;
	private DatagramSocket mDatagramSocket;
	private PrintService mLog;
	private Channel mCurrentChannel;
	private BufferedReader in;

	private String mNickname;

	public DamnSmallUDPChatClient(String serverAddress, String serverPort,
			Channel currentChannel, String nickname) {
		mDatagramSocket = null;
		try {

			mServerAddress = InetAddress.getByName(serverAddress);
			mServerPort = Integer.parseInt(serverPort);
			mDatagramSocket = new DatagramSocket();
			PrintWriter clientLogWriter = new PrintWriter(new FileWriter(
					"client.log"));
			mLog = new PrintService("Client",
					this.mDatagramSocket.getInetAddress(),
					this.mDatagramSocket.getLocalPort(), clientLogWriter);
			mDatagramSocket.setSoTimeout(TIME_OUT);
			mNickname = nickname;
			mCurrentChannel = currentChannel;
			in = new BufferedReader(new InputStreamReader(System.in));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the mServerAddress
	 */
	public InetAddress getmServerAddress() {
		return mServerAddress;
	}

	/**
	 * @param mServerAddress
	 *            the mServerAddress to set
	 */
	public void setmServerAddress(InetAddress mServerAddress) {
		this.mServerAddress = mServerAddress;
	}

	/**
	 * @return the mServerPort
	 */
	public int getmServerPort() {
		return mServerPort;
	}

	/**
	 * @param mServerPort
	 *            the mServerPort to set
	 */
	public void setmServerPort(int mServerPort) {
		this.mServerPort = mServerPort;
	}

	/**
	 * @return the mDatagramSocket
	 */
	public DatagramSocket getmDatagramSocket() {
		return mDatagramSocket;
	}

	/**
	 * @param mDatagramSocket
	 *            the mDatagramSocket to set
	 */
	public void setmDatagramSocket(DatagramSocket mDatagramSocket) {
		this.mDatagramSocket = mDatagramSocket;
	}

	/**
	 * @return the mLog
	 */
	public PrintService getmLog() {
		return mLog;
	}

	/**
	 * @param mLog
	 *            the mLog to set
	 */
	public void setmLog(PrintService mLog) {
		this.mLog = mLog;
	}

	/**
	 * @return the mCurrentChannel
	 */
	public Channel getmCurrentChannel() {
		return mCurrentChannel;
	}

	/**
	 * @param mCurrentChannel
	 *            the mCurrentChannel to set
	 */
	public void setmCurrentChannel(Channel mCurrentChannel) {
		this.mCurrentChannel = mCurrentChannel;
	}

	/**
	 * @return the in
	 */
	public BufferedReader getIn() {
		return in;
	}

	/**
	 * @param in
	 *            the in to set
	 */
	public void setIn(BufferedReader in) {
		this.in = in;
	}

	/**
	 * @return the mNickname
	 */
	public String getmNickname() {
		return mNickname;
	}

	/**
	 * @param mNickname
	 *            the mNickname to set
	 */
	public void setmNickname(String mNickname) {
		this.mNickname = mNickname;
	}

	public void run() {
		try {
			String message = "";
			while ((message = in.readLine()) != null) { // CTRL+D or CTRL+Z to
														// terminate
				mCurrentChannel.print(mNickname + " :   ");
				message = mNickname + " :   " + message;
				this.sendMessage(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

	public void sendMessage(String message) {
		try {
			byte[] msgBytes = message.getBytes();
			DatagramPacket packet = new DatagramPacket(msgBytes,
					msgBytes.length, this.mServerAddress, this.mServerPort);
			mLog.print("trying to contact echo server...");
			this.mDatagramSocket.send(packet);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.mDatagramSocket != null)
				this.mDatagramSocket.close();
		}
	}
}
