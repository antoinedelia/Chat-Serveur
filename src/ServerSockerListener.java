
public class ServerSockerListener {

	public static void main(String[] args)
	{
		Login login = new Login();
		Thread thread1 = new Thread(login);
		thread1.start();
		System.out.println("Serveur en ligne !");
	}

}