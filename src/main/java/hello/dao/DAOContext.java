package hello.dao;

public class DAOContext {
	protected static String dbURL;
	protected static String dbLogin;
	protected static String dbPassword;
	
public DAOContext() {
	// TODO Auto-generated constructor stub
	System.out.print("ok ok");
}

	public static void init(String url,String pass,String log,String driver) {
		try {    
			Class.forName(driver);
			dbLogin =log;
			dbPassword = pass;
			dbURL = url;
		
			
		} catch( Exception exception ) {
			
			exception.printStackTrace();
			
		}
	}
}
