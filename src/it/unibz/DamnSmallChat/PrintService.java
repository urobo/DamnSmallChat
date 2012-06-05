package it.unibz.DamnSmallChat;

import java.io.PrintWriter;
import java.net.InetAddress;

public class PrintService {
	private String name;
	private InetAddress a;
	private int p;
	private PrintWriter outFile;

	public PrintService(String name) {
		super();
		this.name = name;
	}

	public PrintService(String name, InetAddress a, int p, PrintWriter logFile) {
		super();
		this.name = name;
		this.a = a;
		this.p = p;
		this.outFile = logFile;
	}

	public void print(String msg) {
		System.out.println(msg);
	}

	public void log(String msg) {
		outFile.println(name + "(" + a.getHostName() + "," + p + "): " + msg);
	}

	public void close() {
		this.outFile.close();
	}
}
