package nl.hu.ipass.app.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresBaseDao {
	private static Connection conn;
	  protected final Connection getConnection() throws SQLException, ClassNotFoundException {
		  Class.forName("org.postgresql.Driver");
		  conn = DriverManager.getConnection("jdbc:postgresql://localhost/worlddb?user=postgres&password=admin");
		  
		  return conn;

		  } 

}
