package nl.hu.ipass.app.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserPostgresDaoImpl extends PostgresBaseDao implements UserDao {
	
	public String findRoleForUser(String name, String pass) throws ClassNotFoundException, SQLException {
	Connection conn = getConnection();
	String query = "SELECT * FROM KLANT WHERE GEBRUIKERSNAAM = '"+name+"'"+"AND WACHTWOORD = '"+pass+"'"+";";
	PreparedStatement stat = conn.prepareStatement(query);
    ResultSet rs = stat.executeQuery();	
    String var = null;
    int var2 = 0;
    while(rs.next()) {
    	var = rs.getString("ROL");

    }
   
    System.out.println("De rol van deze user is "+var);
	return var;
	}
	

	public boolean add_account(String naam, String tussenvoegsel, String achternaam, String gebruikersnaam, String wachtwoord) throws SQLException, ClassNotFoundException {
		 Connection conn = getConnection();
		 if (naam !=null) {
		 String insert = "INSERT INTO KLANT (naam, tussenvoegsel, achternaam, gebruikersnaam, wachtwoord, rol) "
		 				+ "VALUES(?,?,?,?,?,?)";

         PreparedStatement pstmt = conn.prepareStatement(insert);
         pstmt.setString(1, naam );
         pstmt.setString(2, tussenvoegsel );
         pstmt.setString(3,  achternaam);
         pstmt.setString(4, gebruikersnaam );
         pstmt.setString(5, wachtwoord );
         pstmt.setString(6, "user");
         int result = pstmt.executeUpdate();
         System.out.println("De user met naam "+naam+" is toegevoegd!");
	     return true;
		 }
		 else {
			 System.out.println("User is niet toegevoegd");
			 return false;
		 }
	
	}
}
