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
		//Creating the thread for the client
		ClientThread client = new ClientThread(socket, in, out, login);
		//Add observateur
		client.ajouterObservateur(this);
		clientsPool.add(client);
		new Thread(client).start();
		
		this.login = login;
		sendMessagesToClients(this.login + " vient de se connecter !");
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
