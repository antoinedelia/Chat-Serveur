import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Login implements Runnable {

	private Socket socket;
	private PrintWriter out = null;
	private BufferedReader in = null;
	private String login = null;
	private boolean isLogin = false;
	
	public Login(Socket s)
	{
		this.socket = s;
	}
	
	@Override
	public void run() {
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());
			
			while(!isLogin)
			{
				out.println("Login : ");
				out.flush();
				
				login = in.readLine();
				
//				if(login.equals("antoine"))
//				{
					out.println("Connecté !");
					System.out.println("Bienvenue " + login + " !");
					out.flush();
					isLogin = true;
//				}
//				else
//				{
//					out.println("Erreur !");
//					out.flush();
//				}
			}
			Thread thread3 = new Thread(new ClientThread(socket, login));
			thread3.start();			
		} catch (IOException e) {e.printStackTrace();}
		
		

	}

}
