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
	private ClientsPool pool;
	
	public Login(Socket s)
	{
		this.socket = s;
		this.pool = new ClientsPool();
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
					out.println("Connecté !");
					System.out.println("Bienvenue " + login + " !");
					out.flush();
					isLogin = true;
			}
			this.pool.ajouterClient(socket, in, out, login);
			
			//Thread thread3 = new Thread(new ClientThread(socket, login));
			//thread3.start();			
		} catch (IOException e) {e.printStackTrace();}
		
		

	}

}
