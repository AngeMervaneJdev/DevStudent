package hello.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import hello.models.Article;

public class ArticleDAO extends DAOContext{
	
	public static void verify() throws NamingException {
		if(dbURL==null) {
			InitialContext initialContext = new javax.naming.InitialContext();
		
			 init(initialContext.lookup("java:comp/env/JDBC_URL").toString(),
					  initialContext.lookup("java:comp/env/JDBC_PASSWORD").toString(),
					  initialContext.lookup("java:comp/env/JDBC_LOGIN").toString(),
					  initialContext.lookup("java:comp/env/JDBC_DRIVER").toString());
		}
		
	}

	public static int getArticleCount() throws NamingException {
		
		verify();
		try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ){

			String strSql = "SELECT count(idArticle) FROM T_Articles";
			try ( Statement statement  = connection.createStatement() ) {
				try ( ResultSet resultSet = statement.executeQuery( strSql ) ) {
					resultSet.next();
					return resultSet.getInt( 1 );
				}
			}
			
		} catch ( Exception exception ) {
			
			throw new RuntimeException( exception );
			
		}
	}
	
	public static Article getArticleById( int idArticle ) throws Exception {
		verify();
		
		try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ){
			System.out.println( "connection to the database" );
			String strSql = "SELECT * FROM T_Articles WHERE idArticle=?";
			try ( PreparedStatement statement  = connection.prepareStatement( strSql ) ) {
				statement.setInt( 1, idArticle );
				try ( ResultSet resultSet = statement.executeQuery() ) {
					resultSet.next();
					return new Article(
							resultSet.getInt( "idArticle" ),
							resultSet.getString( "description" ),
							resultSet.getString( "brand" ),
							resultSet.getDouble( "unitaryPrice" )
					);
				}
			}
			
		} catch ( Exception exception ) {
			
			throw new RuntimeException( exception );
			
		}
	}
	
	
	public static ArrayList<Article> getArticles( int studentId ) throws NamingException {
		verify();
		
		try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ){
			ArrayList<Article> articles=new ArrayList<>();

			String strSql = "SELECT * FROM T_Articles WHERE studentId=?";
			try ( PreparedStatement statement  = connection.prepareStatement( strSql ) ) {
				statement.setInt( 1,studentId);
				ResultSet r=statement.executeQuery();
				while(r.next()) {
					articles.add(new Article(
							r.getInt( "IdArticle" ),
							r.getString( "description" ),
							r.getString( "brand" ),
							r.getDouble( "unitaryPrice" )
							));
				}
				
			return articles;
				
			}
			
		} catch ( Exception exception ) {
			
			throw new RuntimeException( exception );
			
		}
	}
	
	
	public static void updateArticle( Article article ) throws NamingException {
		
		verify();
		try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ){

			String strSql = "UPDATE T_Articles SET description=?, brand=?, unitaryPrice=? WHERE IdArticle=?";
			try ( PreparedStatement statement  = connection.prepareStatement( strSql ) ) {
				statement.setString( 1, article.getDescription() );
				statement.setString( 2, article.getBrand() );
				statement.setDouble( 3, article.getUnitaryPrice() );
				statement.setInt( 4, article.getIdArticle() );
				statement.executeUpdate();
			}
			
		} catch ( Exception exception ) {
			
			throw new RuntimeException( exception );
			
		}
	}

	public static void addArticle(Article article,int studentId) throws NamingException {
		// TODO Auto-generated method stub
		verify();
		try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ){

			String strSql = "INSERT INTO T_Articles (description, brand, unitaryPrice,studentId) VALUES (?,?,?,?)";
			try ( PreparedStatement statement  = connection.prepareStatement( strSql ) ) {
				statement.setString( 1, article.getDescription() );
				statement.setString( 2, article.getBrand() );
				statement.setDouble( 3, article.getUnitaryPrice() );
				statement.setInt( 4, studentId );
				statement.execute();
			}
			
			
		} catch ( Exception exception ) {
			
			throw new RuntimeException( exception );
			
		}
	}
	public static int getId() throws NamingException {
		verify();
		try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ){

			String strSql = "SELECT IdArticle FROM T_Articles ORDER BY IdArticle DESC LIMIT 1;";
			try ( PreparedStatement statement  = connection.prepareStatement( strSql ) ) {
				
				ResultSet r= statement.executeQuery();
				r.next();
				return r.getInt("IdArticle");
				
				
				
			}
			
			
		} catch ( Exception exception ) {
			
			throw new RuntimeException( exception );
			
		}
	}
	
}
