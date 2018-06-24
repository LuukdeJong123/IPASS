package nl.hu.ipass.app.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OpmerkingPostgresDaoImpl extends PostgresBaseDao implements OpmerkingDao {
	// Deze functie haalt alle opmerkingen uit de database.
	public List<Opmerking> getOpmerkingen() throws ClassNotFoundException, SQLException{
		ArrayList<Opmerking> lijst_opmerkingen = new ArrayList<Opmerking>();
		//Hier wordt een connectie gelegd met de database en wordt er een statement gemaakt voor de sql query
		Connection conn = getConnection();
		Statement st;
		ResultSet rs = null;
		//Dit de sql query en die wordt dan uitgevoerd in de database.
		String query = "SELECT * FROM OPMERKINGEN";
		st = conn.createStatement();
		rs = st.executeQuery(query);
		//Deze while loop haalt elke keer het productID en de tekst van elke opmerkingen en voegd hem toe aan een lijst.
		while (rs.next()) {
			 Opmerking opmerking = new Opmerking();
			 opmerking.setID(rs.getInt("PRODUCTID"));
			 opmerking.setTekst(rs.getString("TEKST"));
			 lijst_opmerkingen.add(opmerking);
		}

		return lijst_opmerkingen;
	}
	// Deze functie zet een opmerking in de database
	public boolean insertOpmerking(Opmerking opmerking) throws ClassNotFoundException, SQLException {
		//Hier wordt een connectie gelegd met de database en wordt er een statement gemaakt voor de sql query
		Connection conn = getConnection();
		Statement st;
		int rs;
		// Als de opmerking die wordt meegeven in de functie niet null is dan word de query uitgevoerd en print ik in de console dat de opmerking is toegevoegd
		if (opmerking != null) {
			String query = "INSERT INTO OPMERKINGEN (tekst,productID) VALUES(?,?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, opmerking.getTekst() );
	        pstmt.setInt(2, opmerking.getID() );
	        int result = pstmt.executeUpdate();
	        System.out.println("De opmerking met ID "+opmerking.getID()+" is toegevoegd!");
		    return true;
		}
		//als de opmerking null is wordt hij niet toegevoegd aan de database 
		else{
			 System.out.println("Product is niet toegevoegd");
			 return false;
		 }
	}

}
