import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class AccepterClients implements Runnable{

	private ServerSocket socketServer;
	private Socket socket;

	public AccepterClients(ServerSocket s)
	{
		this.socketServer = s;
	}

	@Override
	public void run() {
		try
		{
			while(true)
			{
				this.socket = socketServer.accept();
				//Thread thread2= new Thread(new Login(socket));
				//thread2.start();
			}
		}catch(IOException e){ e.printStackTrace(); }

	}


}