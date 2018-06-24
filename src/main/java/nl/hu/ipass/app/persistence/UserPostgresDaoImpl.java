package nl.hu.ipass.app.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
	public List<User> findAllUsersID() throws ClassNotFoundException, SQLException {
	Connection conn = getConnection();
	ArrayList<User> lijst_users = new ArrayList<User>();
	String query = "SELECT * FROM KLANT";
	PreparedStatement stat = conn.prepareStatement(query);
    ResultSet rs = stat.executeQuery();	
   
    while(rs.next()) {
    	User user = new User();
    	user.setID(rs.getInt("klantid"));
    	user.setGebruikersnaam(rs.getString("gebruikersnaam"));
    	lijst_users.add(user);
    }
	return lijst_users;
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
	
	public boolean add_bestelling(String datum, String tijd, int id) throws ClassNotFoundException, SQLException{
		 Connection conn = getConnection();
		 if (datum !=null) {
			 String insert = "INSERT INTO BESTELLING (datum, tijd,klantid) VALUES( to_date('"+datum+"', 'yyyy-mm-dd'),?,?)";
			 PreparedStatement pstmt = conn.prepareStatement(insert);

		     pstmt.setString(1, tijd );
		     pstmt.setInt(2, id);
		     int result = pstmt.executeUpdate();
		     System.out.println("De bestelling met datum "+datum+" is toegevoegd!");
			 return true;
		 }
		 else {
			 System.out.println("Bestelling is niet toegevoegd");
			 return false;
		 }
	}
}
