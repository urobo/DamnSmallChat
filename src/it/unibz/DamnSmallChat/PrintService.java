
import java.net.InetAddress;

public class PrintService {
	private String name;
	private InetAddress a;
	private int p;
	
	public PrintService(String name, InetAddress a, int p) {
		super();
		this.name = name;
		this.a = a;
		this.p = p;
	}

	public void print(String msg) {
		System.out.println(name+"("+a.getHostName()+","+p+"): "+msg);
	}
}
