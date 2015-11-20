import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientsPool implements Observateur {

	private ArrayList<ClientThread> clientsPool;
	private String login;

	public ClientsPool()
	{
		clientsPool= new ArrayList<ClientThread>();
	}
	
	public void ajouterClient(Socket socket, BufferedReader in, PrintWriter out, String login) {
		ClientThread client = new ClientThread(socket, in, out, login);
		client.ajouterObservateur(this);
		clientsPool.add(client);
		new Thread(client).start();
		
		this.login = login;
		sendMessagesToClients(this.login + " vient de se connecter !");
		
//		this.socket = socket;
//		this.in = in;
//		this.out = out;
		
		
		
	}

	@Override
	public void actualiser(ClientThread client) {
		sendMessagesToClients(client.getMessage());
	}
	
	public void sendMessagesToClients(String message)
	{
		for(ClientThread client: this.clientsPool)
		{
			client.sendMessage(message);
		}
	}



}
