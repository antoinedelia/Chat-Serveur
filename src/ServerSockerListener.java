
public class ServerSockerListener {
	
	//Main class
	public static void main(String[] args)
	{
		//Create a login and launch it
		Login login = new Login();
		Thread thread1 = new Thread(login);
		thread1.start();
		System.out.println("Serveur en ligne !");
	}

}