package it.unibz.DamnSmallChat;

import java.net.*;
import java.io.BufferedReader;
import java.lang.Thread;


public class DamnSmallUDPChatClient extends Thread{
	
	private static int TIME_OUT = 5000;
	
	private InetAddress mServerAddress;
	private int mServerPort;
	private String mMessage;
	private DatagramSocket mDatagramSocket;
	private PrintService mLog;
	private Channel mCurrentChannel;
	private byte[] mMessageBytes;
	private DatagramPacket mDatagramPacket;
	private BufferedReader in;

	private String mNickname;


	public DamnSmallUDPChatClient(String serverAddress, String serverPort, Channel currentChannel, PrintService log, String nickname){
		mDatagramSocket = null;
		try{
			mServerAddress = InetAddress.getByName(serverAddress);
			mServerPort = Integer.parseInt(serverPort);
			mMessage = null;
			mDatagramSocket = new DatagramSocket();
			mLog = log;
			mDatagramSocket.setSoTimeout(TIME_OUT);
			mNickname = nickname;
			mCurrentChannel = currentChannel;
			in = new BufferedReader(new InputStreamReader(System.in));
		}catch (Exception e){
			e.printStackTrace();
		}
	}
		
	
	public void run(){
		try{
			String message = "";
			while( message = in.readLine()) != null) { //CTRL+D or CTRL+Z to terminate
				mCurrentChannel.print(mNickname + " :   ");
				message = mNickname + " :   " + message;
				this.sendMessage(message);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{

		}
	}


	public void sendMessage(String message){			
			byte[] msgBytes = msg.getBytes();
			DatagramPacket packet = new DatagramPacket(msgBytes,msgBytes.length,serverAddress,serverPort);
			mLog.print("trying to contact echo server...");
			ds.send(packet);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(ds != null)
				ds.close();
		}
	}
}
