import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Login implements Runnable {

	private ServerSocket serverSocket;
	private PrintWriter out = null;
	private BufferedReader in = null;
	private ClientsPool pool;

	public Login()
	{
		this.pool = new ClientsPool();
		try{
			//Server listening on a port
			this.serverSocket = new ServerSocket(63);
		}catch(IOException e){e.printStackTrace();}
	}

	@Override
	public void run() {
		try {
			while(!Thread.interrupted())
			{
				//Accept the client
				Socket socket = this.serverSocket.accept();
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream());
				out.println("Login : ");
				out.flush();
				
				//We get the client's login
				String login = in.readLine();
				out.println("Connecté !");
				System.out.println("Bienvenue " + login + " !");
				out.flush();
				//Adding the client to our pool
				this.pool.ajouterClient(socket, in, out, login);

			}		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
