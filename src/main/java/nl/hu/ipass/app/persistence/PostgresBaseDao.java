package nl.hu.ipass.app.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//Deze klasse legt de connectie met de database van heroku. Er is een postgresql driver nodig om deze klasse te laten werken. 
//En met de connection heb je de database naam nodig en de port.
public class PostgresBaseDao {
	private static Connection conn;
	  protected final Connection getConnection() throws SQLException, ClassNotFoundException {
		  Class.forName("org.postgresql.Driver");
		  conn = DriverManager.getConnection("jdbc:postgresql://localhost/IPASS?user=postgres&password=admin");
		  //Dit zijn de beide connecties die ik leg met de database voor heroku en mijn eigen localhost
		  //jdbc:postgresql://localhost/IPASS?user=postgres&password=admin
		  //jdbc:postgresql://ec2-54-247-87-201.eu-west-1.compute.amazonaws.com/d7aaef90cv9mbg?user=todrtwfcbsyfmj&password=5046b4f0d816f652d98139b3fd29dd3605ac3e991ba6b802e924209c5408d659
		  return conn;

		  } 

}
