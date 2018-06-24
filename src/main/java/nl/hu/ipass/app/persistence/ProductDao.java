package nl.hu.ipass.app.persistence;

import java.sql.SQLException;
import java.util.List;
//Dit is een interface klasse. Zo kan je elke klasse makkelijk aanpassen per database zodat je niet een hele klasse opnieuw moet schrijven omdat je van database veranderd
public interface ProductDao {
	public List<Product> findAll() throws SQLException, ClassNotFoundException;
	public boolean save(Product product) throws SQLException, ClassNotFoundException;
	
}
