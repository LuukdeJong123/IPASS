package nl.hu.ipass.app.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class WinkelwagenPostgresDaoImpl extends PostgresBaseDao implements WinkelwagenDao {

	// Deze functie haalt alle producten uit de database die in de winkelwagen hoort..
	public List<Product> getWinkelwagen() throws ClassNotFoundException, SQLException {
		ArrayList<Product> lijst_producten = new ArrayList<Product>();
		//Hier wordt een connectie gelegd met de database en wordt er een statement gemaakt voor de sql query
		Connection conn = getConnection();
		Statement st;
		ResultSet rs = null;
		
		String query = "SELECT * FROM WINKELWAGEN";
		st = conn.createStatement();
		rs = st.executeQuery(query);
		//Deze while loop haalt elke keer alle informatie op van een product en zet het in een lijst.
		while (rs.next()) {
			Product product = new Product();
			product.setID(rs.getInt("WINKELWAGENID"));
			product.setNaam(rs.getString("NAAM"));
			product.setPrijs(rs.getDouble("PRIJS"));
			lijst_producten.add(product);		
		}
		conn.close();
		return lijst_producten;
	}
	// Deze functie zet een product in de winkelwagen.
	public boolean insertWinkelwagen(Product product) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		//Als het product dat wordt meegeven niet null is mag dit worden uitgevoerd
		if (product != null) {
			String query = "INSERT INTO WINKELWAGEN (naam,prijs) VALUES(?,?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, product.getNaam() );
	        pstmt.setDouble(2,  product.getPrijs());
	        int result = pstmt.executeUpdate();
	        System.out.println("Het product met naam "+product.getNaam()+" is toegevoegd aan de winkelwagen!");
	        conn.close();
		    return true;
		}
		//Als het product null is gebeurd er natuurlijk niks met de database
		else{
			 System.out.println("Product is niet toegevoegd");
			 conn.close();
			 return false;
		 }
		
	}
	// Deze functie verwijderd een product uit de database.
	public boolean deleteWinkelwagen(int code) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		//De code die wordt meegeven moet niet null zijn anders wordt er niks verwijderd 
		if (code != 0) {
			String query = "DELETE FROM WINKELWAGEN WHERE WINKELWAGENID = (?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
	        pstmt.setInt(1,code);
	        int result = pstmt.executeUpdate();
	        System.out.println("Het product met naam "+code+" is verwijderd uit de winkelwagen");
	        conn.close();
	        return true;
		}
		else{
			 System.out.println("Product is niet verwijderd");
			 conn.close();
			 return false;
		 }
		
	}

}
