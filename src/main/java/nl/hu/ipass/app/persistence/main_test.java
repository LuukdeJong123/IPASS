package nl.hu.ipass.app.persistence;

import java.sql.SQLException;



public class main_test {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
	UserPostgresDaoImpl u1 = new UserPostgresDaoImpl();
	ProductPostgresDaoImpl p1 = new ProductPostgresDaoImpl();
	OpmerkingPostgresDaoImpl o1 = new OpmerkingPostgresDaoImpl();
	WinkelwagenPostgresDaoImpl w1 = new WinkelwagenPostgresDaoImpl();
	
	w1.getWinkelwagen();
	for (Product product : w1.getWinkelwagen()) {
		System.out.println("Land "+product.getNaam());
	}
	Product product = new Product();
	product.setNaam("Smirnoff Vodka");
	product.setPrijs(19.99);
	w1.insertWinkelwagen(product);
	}
}
	
	
