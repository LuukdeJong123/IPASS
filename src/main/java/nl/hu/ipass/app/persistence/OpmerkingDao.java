package nl.hu.ipass.app.persistence;

import java.sql.SQLException;
import java.util.List;

public interface OpmerkingDao {
	public List<Opmerking> getOpmerkingen() throws ClassNotFoundException, SQLException;
	public boolean insertOpmerking(Opmerking opmerking) throws ClassNotFoundException, SQLException;
}
