import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientThread implements Runnable{

	private Socket socket = null;
	private BufferedReader in = null;
	private PrintWriter out = null;
	private String login = null;

	public ClientThread(Socket s, String login)
	{
		this.socket = s;
		this.login = login;
	}

	@Override
	public void run() {
		try{
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());
			while(true)
			{
				String messageSent = in.readLine();
				System.out.println(messageSent);
			}
			
//	
//			Thread thread4 = new Thread(new LogWriter(in, login));
//			thread4.start();
//			Thread thread5 = new Thread(new LogWriter(out));
//			thread5.start();
		} catch(Exception e){ e.printStackTrace(); }


	}

}