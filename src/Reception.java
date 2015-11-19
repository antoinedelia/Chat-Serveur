import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Reception implements Runnable {
	
	private BufferedReader in = null;
	private String login = null;
	private Socket socket = null;

	public Reception (Socket s, BufferedReader in, String login)
	{
		this.socket  = s;
		this.in = in;
		this.login = login;
	}

	@Override
	public void run() {
		
		while(true)
		{
			try {
				String message = in.readLine();
				System.out.println(login + " : " + message);
			} catch (IOException e) {
				try {
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		}

	}

}
