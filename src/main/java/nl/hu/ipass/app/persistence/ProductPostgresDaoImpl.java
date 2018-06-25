package nl.hu.ipass.app.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductPostgresDaoImpl extends PostgresBaseDao implements ProductDao {

	// Deze functie haalt alle producten uit de database.
	public List<Product> findAll() throws SQLException, ClassNotFoundException {
		ArrayList<Product> lijst_producten = new ArrayList<Product>();
		//Hier wordt een connectie gelegd met de database en wordt er een statement gemaakt voor de sql query
		Connection conn = getConnection();
		Statement st;
		ResultSet rs = null;
		
		String query = "SELECT * FROM PRODUCT";
		st = conn.createStatement();
		rs = st.executeQuery(query);
		//Deze while loop haalt elke keer alle informatie op van een product en zet het in een lijst.
		while (rs.next()) {
			
			Product product = new Product();
			product.setID(rs.getInt("PRODUCTID"));
			product.setNaam(rs.getString("NAAM"));
			product.setPrijs(rs.getDouble("PRIJS"));
			product.setSmaak(rs.getString("SMAAK"));
			product.setAlcoholpercentage(rs.getDouble("ALCOHOLPERCENTAGE"));
			lijst_producten.add(product);		
		}
		conn.close();
		return lijst_producten;
	}



	// Deze functie haalt alle producten uit de database.
	public boolean save(Product product) throws SQLException, ClassNotFoundException {
		//Hier wordt een connectie gelegd met de database en wordt er een statement gemaakt voor de sql query
		Connection conn = getConnection();
		//Als het product dat wordt meegeven niet null is mag dit worden uitgevoerd
		if (product != null) {
			String query = "INSERT INTO PRODUCT (naam,smaak,prijs,alcoholpercentage) VALUES(?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, product.getNaam() );
	        pstmt.setString(2, product.getSmaak() );
	        pstmt.setDouble(3,  product.getPrijs());
	        pstmt.setDouble(4, product.getAlcoholpercentage());
	        int result = pstmt.executeUpdate();
	        System.out.println("Het product met naam "+product.getNaam()+" is toegevoegd!");
	        conn.close();
		    return true;
		}
		else{
			 System.out.println("Product is niet toegevoegd");
			 conn.close();
			 return false;
		 }
	}



}
