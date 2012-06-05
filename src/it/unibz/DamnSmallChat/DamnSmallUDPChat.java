package it.unibz.DamnSmallChat;

public class DamnSmallUDPChat {
	private static final String LOCALHOST = "localhost";
	private static final String DEBUG_NICKNAME = "debug";
	private static final String PORT = "1833";
	private static final String DEFAULT_NAME = "Default Channel";
	private static final String DEBUG_ARGS[] = { PORT, LOCALHOST,
			DEBUG_NICKNAME, PORT };

	public static void main(String args[]) {
		if (args.length < 4) {
			System.out
					.println("usage java DamnSmallUDPChat <RemoteClientServerPort::int> <RemoteClientServerAddress::String> <Nickname::String> <LocalClientServerPort::int>");
			System.out.println("using static Args for debug");

			args = new String[4];
			for (int i = 0; i < DEBUG_ARGS.length; i++) {
				args[i] = DEBUG_ARGS[i];
			}
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