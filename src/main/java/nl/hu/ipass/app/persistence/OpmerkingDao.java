package nl.hu.ipass.app.persistence;

import java.sql.SQLException;
import java.util.List;
//Dit is een interface klasse. Zo kan je elke klasse makkelijk aanpassen per database zodat je niet een hele klasse opnieuw moet schrijven omdat je van database veranderd
public interface OpmerkingDao {
	public List<Opmerking> getOpmerkingen() throws ClassNotFoundException, SQLException;
	public boolean insertOpmerking(Opmerking opmerking) throws ClassNotFoundException, SQLException;
}
