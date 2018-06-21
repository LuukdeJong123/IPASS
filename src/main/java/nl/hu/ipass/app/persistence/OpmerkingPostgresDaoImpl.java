package nl.hu.ipass.app.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OpmerkingPostgresDaoImpl extends PostgresBaseDao implements OpmerkingDao {

	public List<Opmerking> getOpmerkingen() throws ClassNotFoundException, SQLException{
		ArrayList<Opmerking> lijst_opmerkingen = new ArrayList<Opmerking>();
		Connection conn = getConnection();
		Statement st;
		ResultSet rs = null;
		
		String query = "SELECT * FROM OPMERKINGEN";
		st = conn.createStatement();
		rs = st.executeQuery(query);
		String var;
		while (rs.next()) {
			 Opmerking opmerking = new Opmerking();
			 opmerking.setID(rs.getInt("PRODUCTID"));
			 opmerking.setTekst(rs.getString("TEKST"));
			 lijst_opmerkingen.add(opmerking);
		}

		return lijst_opmerkingen;
	}
	
	public boolean insertOpmerking(Opmerking opmerking) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		Statement st;
		int rs;
		if (opmerking != null) {
			String query = "INSERT INTO OPMERKINGEN (tekst,productID) VALUES(?,?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, opmerking.getTekst() );
	        pstmt.setInt(2, opmerking.getID() );
	        int result = pstmt.executeUpdate();
	        System.out.println("De opmerking met ID "+opmerking.getID()+" is toegevoegd!");
		    return true;
		}
		else{
			 System.out.println("Product is niet toegevoegd");
			 return false;
		 }
	}

}
