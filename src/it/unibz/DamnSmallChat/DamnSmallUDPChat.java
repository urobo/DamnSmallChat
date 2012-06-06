package it.unibz.DamnSmallChat;

public class DamnSmallUDPChat {
	private static final String LOCALHOST = "localhost";
	
	private static final String DEFAULT_NAME = "Default Channel";
	private static final String DEBUG_NICKNAME1 = "debug1";
	private static final String DEBUG_NICKNAME2 = "debug2";
	private static final String PORT1 = "1833";
	private static final String PORT2 = "1834";
	
	private static final String DEBUG_ARGS1[] = { PORT2, LOCALHOST,
		DEBUG_NICKNAME1, PORT1 };
	private static final String DEBUG_ARGS2[] = { PORT1, LOCALHOST,
		DEBUG_NICKNAME2, PORT2 };

	public static void main(String args[]) {
		if (args.length < 4) {
			System.out
					.println("usage java DamnSmallUDPChat <RemoteClientServerPort::int> <RemoteClientServerAddress::String> <Nickname::String> <LocalClientServerPort::int>");
			args = DEBUG_ARGS1;
		}
		Channel currentChannel = new Channel(new PrintService(DEFAULT_NAME));

		DamnSmallUDPChatClient client = new DamnSmallUDPChatClient(args[1],
				args[0], currentChannel, args[2]);
		DamnSmallUDPChatServer server = new DamnSmallUDPChatServer(LOCALHOST,
				args[3], currentChannel);

		client.start();
		server.start();

	}
}