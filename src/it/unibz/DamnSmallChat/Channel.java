package it.unibz.DamnSmallChat;

public class Channel {
	private PrintService out;

	public Channel(PrintService out) {
		this.out = out;
	}

	public void print(String msg) {
		this.out.print(msg);
	}

}