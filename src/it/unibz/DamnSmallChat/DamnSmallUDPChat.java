package it.unibz.DamnSmallChat;

public class DamnSmallUDPChat {
	// usage java DamnSmallUDPChat <RemoteClientServerPort::int> <RemoteClientServerAddress::String> <Nickname::String> <LocalClientServerPort::int>
	public int main (String args[]){
		if (args.length <4){
			System.out.println("usage java DamnSmallUDPChat <RemoteClientServerPort::int> <RemoteClientServerAddress::String> <Nickname::String> <LocalClientServerPort::int>");
			System.exit(1);
		}else{
			
		}
		return 0;
	}
}