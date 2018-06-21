package nl.hu.ipass.app.persistence;

import java.sql.SQLException;

public interface UserDao {
	public String findRoleForUser(String name, String pass) throws ClassNotFoundException, SQLException;
	public boolean add_account(String naam, String tussenvoegsel, String achternaam, String gebruikersnaam, String wachtwoord) throws SQLException, ClassNotFoundException;
}
