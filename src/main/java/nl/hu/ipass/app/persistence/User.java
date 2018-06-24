package nl.hu.ipass.app.persistence;

public class User {
	private int ID;
	private String gebruikersnaam;
	
	public int getID() {
		return ID;
	}
	public void setID(int id) {
		ID = id;
	}
	
	public String getGebruikersnaam() {
		return gebruikersnaam;
	}
	public void setGebruikersnaam(String gn) {
		gebruikersnaam=gn ;
	}
}
