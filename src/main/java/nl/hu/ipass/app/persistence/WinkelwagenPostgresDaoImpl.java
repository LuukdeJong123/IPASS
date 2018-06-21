package nl.hu.ipass.app.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class WinkelwagenPostgresDaoImpl extends PostgresBaseDao implements WinkelwagenDao {

	
	public List<Product> getWinkelwagen() throws ClassNotFoundException, SQLException {
		ArrayList<Product> lijst_producten = new ArrayList<Product>();
		Connection conn = getConnection();
		Statement st;
		ResultSet rs = null;
		
		String query = "SELECT * FROM WINKELWAGEN";
		st = conn.createStatement();
		rs = st.executeQuery(query);
		while (rs.next()) {
			Product product = new Product();
			product.setID(rs.getInt("WINKELWAGENID"));
			product.setNaam(rs.getString("NAAM"));
			product.setPrijs(rs.getDouble("PRIJS"));
			lijst_producten.add(product);		
		}
		return lijst_producten;
	}
	
	public boolean insertWinkelwagen(Product product) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		Statement st;
		int rs;
		if (product != null) {
			String query = "INSERT INTO WINKELWAGEN (naam,prijs) VALUES(?,?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, product.getNaam() );
	        pstmt.setDouble(2,  product.getPrijs());
	        int result = pstmt.executeUpdate();
	        System.out.println("Het product met naam "+product.getNaam()+" is toegevoegd aan de winkelwagen!");
		    return true;
		}
		else{
			 System.out.println("Product is niet toegevoegd");
			 return false;
		 }
	}

}
