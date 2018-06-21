package nl.hu.ipass.app.persistence;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
	public List<Product> findAll() throws SQLException, ClassNotFoundException;
	public boolean save(Product product) throws SQLException, ClassNotFoundException;
	
}
