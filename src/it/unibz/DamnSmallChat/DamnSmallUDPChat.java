package it.unibz.DamnSmallChat;



public class DamnSmallUDPChat {
	private static final String LOCALHOST = "localhost";
	private static final String DEFAULT_NAME = "Default Channel";
	
	// usage java DamnSmallUDPChat <RemoteUserServerPort::int> <RemoteUserServerAddress::String> <Nickname::String> <LocalUserServerPort::int>
	public static void main (String args[]){
		if (args.length <4){
			System.out.println("usage java DamnSmallUDPChat <RemoteClientServerPort::int> <RemoteClientServerAddress::String> <Nickname::String> <LocalClientServerPort::int>");
			System.exit(1);
		}else{
			Channel currentChannel = new Channel(new PrintService(DEFAULT_NAME));
			
			DamnSmallUDPChatClient client = new DamnSmallUDPChatClient(args[1], args[0], currentChannel, args[2]);
			DamnSmallUDPChatServer server = new DamnSmallUDPChatServer(LOCALHOST, args[3], currentChannel);
			
			client.start();
			server.start();
		}
	}
}