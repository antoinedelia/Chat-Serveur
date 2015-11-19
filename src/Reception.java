import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Reception implements Runnable {

	private BufferedReader in;
	private PrintWriter out;
	private String message, login = null;
	private Socket socket = null;

	public Reception (Socket s, BufferedReader in, String login, PrintWriter out)
	{
		this.socket  = s;
		this.in = in;
		this.login = login;
		this.out = out;
	}

	@Override
	public void run() {

		while(true)
		{
			try {
				message = in.readLine();
				if(message != null && !message.isEmpty())
				{
					System.out.println(login + " : " + message);
					out.println(login + " : " + message);
					out.flush();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
