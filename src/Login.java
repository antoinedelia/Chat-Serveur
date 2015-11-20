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
			this.serverSocket = new ServerSocket(63);
		}catch(IOException e){e.printStackTrace();}
	}

	@Override
	public void run() {
		try {
			while(true)
			{
				Socket socket = this.serverSocket.accept();
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream());
				out.println("Login : ");
				out.flush();

				String login = in.readLine();
				out.println("Connecté !");
				System.out.println("Bienvenue " + login + " !");
				out.flush();
				this.pool.ajouterClient(socket, in, out, login);

			}
			//Thread thread3 = new Thread(new ClientThread(socket, login));
			//thread3.start();			
		} catch (IOException e) {e.printStackTrace();}



	}

}
