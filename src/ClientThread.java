import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientThread implements Runnable, Observable{

	private Socket socket = null;
	private BufferedReader in = null;
	private PrintWriter out = null;
	private String message, login = null;
	private List<Observateur> observeurs = new ArrayList<Observateur>();

	public ClientThread(Socket s, BufferedReader in, PrintWriter out, String login)
	{
		this.socket = s;
		this.in = in;
		this.out = out;
		this.login = login;
	}

	@Override
	public void run() {
		try{
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());
			
			while(!Thread.interrupted())
			{
				//Receiving a message...
				message = this.login + " : " + in.readLine();
				//...and send it to all clients
				notifierObservateurs();	
			}
			
			
		} catch(Exception e){ 
			stop();
			e.printStackTrace(); 
		}


	}

	private void stop() {
		Thread.interrupted();
		while(!Thread.interrupted()){}
		out.flush();
		try {
			in.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void ajouterObservateur(Observateur o) {
		observeurs.add(o);
		
	}

	@Override
	public void supprimerObservateur(Observateur o) {
		observeurs.remove(o);
		
	}

	@Override
	public void notifierObservateurs() {
		for(Observateur o: observeurs)
		{
			o.actualiser(this);
		}
		
	}

	public synchronized void sendMessage(String message) {
		out.println(message);
		out.flush();
		
	}

	public String getMessage() {
		return this.message;
	}

}
