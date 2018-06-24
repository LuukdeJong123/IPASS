package nl.hu.ipass.app.persistence;

import java.sql.SQLException;



public class main_test {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
	UserPostgresDaoImpl u1 = new UserPostgresDaoImpl();
	ProductPostgresDaoImpl p1 = new ProductPostgresDaoImpl();
	OpmerkingPostgresDaoImpl o1 = new OpmerkingPostgresDaoImpl();
	WinkelwagenPostgresDaoImpl w1 = new WinkelwagenPostgresDaoImpl();
	
	
	
	for (User country : u1.findAllUsersID()) {
		System.out.println("Land "+country.getID());
	}
}
}
	
	
