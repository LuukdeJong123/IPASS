package nl.hu.ipass.app.persistence;
//Dit is een normale POJO klasse alles spreekt voor zich. Er zijn getters en setters voor de attributen in de database
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
