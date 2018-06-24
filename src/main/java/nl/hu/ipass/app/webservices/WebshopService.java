package nl.hu.ipass.app.webservices;

import java.sql.SQLException;
import java.util.List;

import nl.hu.ipass.app.persistence.Opmerking;
import nl.hu.ipass.app.persistence.OpmerkingPostgresDaoImpl;
import nl.hu.ipass.app.persistence.Product;
import nl.hu.ipass.app.persistence.ProductPostgresDaoImpl;
import nl.hu.ipass.app.persistence.User;
import nl.hu.ipass.app.persistence.UserPostgresDaoImpl;
import nl.hu.ipass.app.persistence.WinkelwagenPostgresDaoImpl;

public class WebshopService {
	public List<Product> getAllProducten() throws SQLException, ClassNotFoundException {
		ProductPostgresDaoImpl p1 = new ProductPostgresDaoImpl();
		p1.findAll();
		return p1.findAll();
	}
	public Product createProduct(Product product) throws ClassNotFoundException, SQLException {
		ProductPostgresDaoImpl p1 = new ProductPostgresDaoImpl();
		p1.save(product);
		return product;
	}
	public List<Opmerking> getOpmerkingen() throws ClassNotFoundException, SQLException {
		OpmerkingPostgresDaoImpl o1 = new OpmerkingPostgresDaoImpl();
		return o1.getOpmerkingen();
	}
	public Opmerking createOpmerking(Opmerking opmerking) throws ClassNotFoundException, SQLException {
		OpmerkingPostgresDaoImpl o1 = new OpmerkingPostgresDaoImpl();
		o1.insertOpmerking(opmerking);
		return opmerking;
	}
	
	public List<Product> getWinkelwagen() throws ClassNotFoundException, SQLException {
		WinkelwagenPostgresDaoImpl w1 = new WinkelwagenPostgresDaoImpl();
		return w1.getWinkelwagen();
	}
	public Product insertWinkelwagen(Product product) throws ClassNotFoundException, SQLException {
		WinkelwagenPostgresDaoImpl w1 = new WinkelwagenPostgresDaoImpl();
		w1.insertWinkelwagen(product);
		return product;
	}
	
	public boolean deleteWinkelwagen(int code) throws ClassNotFoundException, SQLException {
		WinkelwagenPostgresDaoImpl w1 = new WinkelwagenPostgresDaoImpl();
		if(code != 0) {
			w1.deleteWinkelwagen(code);
			return true;
		}
		else {
			return false;
		}
	}
	public boolean insertBestelling(String datum, String tijd, int id) throws ClassNotFoundException, SQLException {
		UserPostgresDaoImpl u1 = new UserPostgresDaoImpl();
		if(datum != null) {
			u1.add_bestelling(datum, tijd, id);
			return true;
		}
		else {
			return false;
		}
	}
	public List<User> getID() throws ClassNotFoundException, SQLException {
		UserPostgresDaoImpl u1 = new UserPostgresDaoImpl();
		return u1.findAllUsersID();
		
	}
	
}
