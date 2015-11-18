import java.io.BufferedReader;
import java.io.PrintWriter;

public  class LogWriter implements Runnable {
	
	private BufferedReader in = null;
	private PrintWriter out = null;
	private String login = null;
	
	private static volatile LogWriter instance = null;
	
	private LogWriter() {
        super();
    }
	
	LogWriter (PrintWriter out)
	{
		this.out = out;
	}
	
	LogWriter (BufferedReader in, String login)
	{
		this.in = in;
		this.login = login;
	}
	
	public final static LogWriter getInstance() {
        if (LogWriter.instance == null) {
           synchronized(LogWriter.class) {
             if (LogWriter.instance == null) {
               LogWriter.instance = new LogWriter();
             }
           }
        }
        return LogWriter.instance;
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
