package nl.hu.ipass.app.persistence;
//Dit is een normale POJO klasse alles spreekt voor zich. Er zijn getters en setters voor de attributen in de database
public class Opmerking {
	private int ID;
	private String tekst;
	
	public int getID() {
		return ID;
	}
	public void setID(int id) {
		ID = id;
	}
	
	public String getTekst() {
		return tekst;
	}
	public void setTekst(String tk) {
		tekst=tk ;
	}
}
