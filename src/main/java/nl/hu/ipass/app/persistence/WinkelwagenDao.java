package nl.hu.ipass.app.persistence;

import java.sql.SQLException;
import java.util.List;

public interface WinkelwagenDao {
	public List<Product> getWinkelwagen() throws ClassNotFoundException, SQLException;
	public boolean insertWinkelwagen(Product product) throws ClassNotFoundException, SQLException;
}
