package nl.hu.ipass.app.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserPostgresDaoImpl extends PostgresBaseDao implements UserDao {
	//Deze functie zoekt een rol met de gebruikersnaam en wachtwoord.
	public String findRoleForUser(String name, String pass) throws ClassNotFoundException, SQLException {
	//Hier wordt een connectie gelegd met de database en wordt er een statement gemaakt voor de sql query
	Connection conn = getConnection();
	String query = "SELECT * FROM KLANT WHERE GEBRUIKERSNAAM = '"+name+"'"+"AND WACHTWOORD = '"+pass+"'"+";";
	PreparedStatement stat = conn.prepareStatement(query);
    ResultSet rs = stat.executeQuery();	
    String var = null;
    int var2 = 0;
    //Hier geef ik alleen de rol mee zodat ik in de AuthenticationResource kan checken wat er mogelijk is voor de gebruiker gebaseerd op rol.
    while(rs.next()) {
    	var = rs.getString("ROL");

    }
   
    System.out.println("De rol van deze user is "+var);
    conn.close();
	return var;
	}
	//Deze functie zoekt alle ID's van de klanten.
	public List<User> findAllUsersID() throws ClassNotFoundException, SQLException {
	//Hier wordt een connectie gelegd met de database en wordt er een statement gemaakt voor de sql query
	Connection conn = getConnection();
	ArrayList<User> lijst_users = new ArrayList<User>();
	String query = "SELECT * FROM KLANT";
	PreparedStatement stat = conn.prepareStatement(query);
    ResultSet rs = stat.executeQuery();	
   //Hier wordt alleen de klantid en gebruikersnaam meegeven want die heb je nodig om te kijken welke user is ingelogd.
    while(rs.next()) {
    	User user = new User();
    	user.setID(rs.getInt("klantid"));
    	user.setGebruikersnaam(rs.getString("gebruikersnaam"));
    	lijst_users.add(user);
    }
    conn.close();
	return lijst_users;
	}
	
	//Deze functie voegt een account toe aan de database.
	public boolean add_account(String naam, String tussenvoegsel, String achternaam, String gebruikersnaam, String wachtwoord) throws SQLException, ClassNotFoundException {
		//Hier wordt een connectie gelegd met de database en wordt er een statement gemaakt voor de sql query
		Connection conn = getConnection();
		 if (naam !=null) {
			 String insert = "INSERT INTO KLANT (naam, tussenvoegsel, achternaam, gebruikersnaam, wachtwoord, rol) "
			 				+ "VALUES(?,?,?,?,?,?)";
			 //Als de naam die wordt meegegeven niet null is mag deze statement uitgevoerd worden
	         PreparedStatement pstmt = conn.prepareStatement(insert);
	         //De setString methode kijkt in de prepared statement en vult de naam of achternaam in bij het vraagteken zo maak je minder snel een fout.
	         pstmt.setString(1, naam );
	         pstmt.setString(2, tussenvoegsel );
	         pstmt.setString(3,  achternaam);
	         pstmt.setString(4, gebruikersnaam );
	         pstmt.setString(5, wachtwoord );
	         pstmt.setString(6, "user");
	         int result = pstmt.executeUpdate();
	         System.out.println("De user met naam "+naam+" is toegevoegd!");
	         conn.close();
		     return true;
		 }
		 //Als de naam null is wordt de user niet toegevoegd
		 else {
			 System.out.println("User is niet toegevoegd");
			 conn.close();
			 return false;
		 }
	
	}
	//Deze functie voegt een bestelling toe aan de database.
	//De bestelling staat in de deze klasse want er moet een klant id meegeven worden
	public boolean add_bestelling(String datum, String tijd, int id) throws ClassNotFoundException, SQLException{
		//Hier wordt een connectie gelegd met de database en wordt er een statement gemaakt voor de sql query
		 Connection conn = getConnection();
		 if (datum !=null) {
			 String insert = "INSERT INTO BESTELLING (datum, tijd,klantid) VALUES( to_date('"+datum+"', 'yyyy-mm-dd'),?,?)";
			 PreparedStatement pstmt = conn.prepareStatement(insert);
			
		     pstmt.setString(1, tijd );
		     pstmt.setInt(2, id);
		     int result = pstmt.executeUpdate();
		     System.out.println("De bestelling met datum "+datum+" is toegevoegd!");
		     conn.close();
			 return true;
		 }
		 else {
			 System.out.println("Bestelling is niet toegevoegd");
			 conn.close();
			 return false;
		 }
	}
}
