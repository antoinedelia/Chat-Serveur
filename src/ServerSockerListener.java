import java.io.IOException;
import java.net.ServerSocket;

public class ServerSockerListener {

	public static void main(String[] args)
	{
		ServerSocket socketServer;
		try {
			socketServer = new ServerSocket(63);
			Thread thread1 = new Thread(new AccepterClients(socketServer));
			thread1.start();
			System.out.println("Serveur en ligne !");
		} catch (IOException e){ e.printStackTrace(); }
	}

}