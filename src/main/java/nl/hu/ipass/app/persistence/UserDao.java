package nl.hu.ipass.app.persistence;

import java.sql.SQLException;
//Dit is een interface klasse. Zo kan je elke klasse makkelijk aanpassen per database zodat je niet een hele klasse opnieuw moet schrijven omdat je van database veranderd
public interface UserDao {
	public String findRoleForUser(String name, String pass) throws ClassNotFoundException, SQLException;
	public boolean add_account(String naam, String tussenvoegsel, String achternaam, String gebruikersnaam, String wachtwoord) throws SQLException, ClassNotFoundException;
}
