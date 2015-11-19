import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Emission implements Runnable {
	
	
	
	private PrintWriter out = null;
	private Socket socket = null;

	public Emission (Socket s, PrintWriter out)
	{
		this.socket  = s;
		this.out = out;
	}

	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		
		while(true)
		{
			String message = sc.nextLine();
			out.println("Serveur : " + message);
			out.flush();
		}

	}

}
