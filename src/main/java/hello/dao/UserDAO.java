package hello.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.SessionScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import hello.models.Student;

@SessionScoped
public class UserDAO extends DAOContext{

	public static Student isValidLogin( String firstName, String password ) throws NamingException {
		
		if(dbURL==null) {
			InitialContext initialContext = new javax.naming.InitialContext();
		
			 init(initialContext.lookup("java:comp/env/JDBC_URL").toString(),
					  initialContext.lookup("java:comp/env/JDBC_PASSWORD").toString(),
					  initialContext.lookup("java:comp/env/JDBC_LOGIN").toString(),
					  initialContext.lookup("java:comp/env/JDBC_DRIVER").toString());
		}
		try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ) {
			//String strSql = "SELECT * FROM T_Users WHERE login='" + login + "' AND password='" + password + "'";
			String strSql = "SELECT * FROM T_Users WHERE firstName=? AND password=?";
			try ( PreparedStatement statement  = connection.prepareStatement( strSql ) ) {
			 statement.setString( 1, firstName );
			statement.setString( 2, password );
				try ( ResultSet resultSet = statement.executeQuery() ) {
					if ( resultSet.next() ) {
						return new Student(
								resultSet.getInt( "studentId" ),
								resultSet.getString( "firstName" ),
								resultSet.getString( "lastName" ),
								resultSet.getString( "language" ),
								resultSet.getString( "country" ),
								new ArrayList<String>(Arrays.asList(resultSet.getString("knowsLanguages").split(" ")))
						);
					} else {
						return null;
					}
				}
			}
		} catch ( Exception exception ) {
			throw new RuntimeException( exception );
		}
	}
	public static boolean addUser(  String firstName, String lastName, String country, String language, List<String> knowsLanguages, String password ) throws NamingException {
		if(dbURL==null) {
			InitialContext initialContext = new javax.naming.InitialContext();
		
			 init(initialContext.lookup("java:comp/env/JDBC_URL").toString(),
					  initialContext.lookup("java:comp/env/JDBC_PASSWORD").toString(),
					  initialContext.lookup("java:comp/env/JDBC_LOGIN").toString(),
					  initialContext.lookup("java:comp/env/JDBC_DRIVER").toString());
		}
		
		try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ) {
			//String strSql = "SELECT * FROM T_Users WHERE login='" + login + "' AND password='" + password + "'";
			String strSql = "INSERT INTO T_Users (firstName, lastName,language,knowsLanguages,password,country) VALUES (?,?,?,?,?,?)";
			try ( PreparedStatement statement  = connection.prepareStatement(strSql) ) {
				statement.setString( 1, firstName );
				statement.setString( 2, lastName );
				statement.setString( 3, language );
				statement.setString( 4,  String.join(" ", knowsLanguages));
				statement.setString( 5, password );
				statement.setString( 6, country );
				return statement.execute() ;
				
			}
			
		} catch ( Exception exception ) {
			throw new RuntimeException( exception );
		}
	
	}
	
}
