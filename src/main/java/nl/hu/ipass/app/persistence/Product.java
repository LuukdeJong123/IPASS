package nl.hu.ipass.app.persistence;
//Dit is een normale POJO klasse alles spreekt voor zich. Er zijn getters en setters voor de attributen in de database
public class Product {
	private int ID;
	private String naam;
	private String smaak;
	private double prijs;
	private double alcoholpercentage;
	
	public int getID() {
		return ID;
	}
	public void setID(int id) {
		ID = id;
	}
	public String getNaam() {
		return naam;
	}
	public void setNaam(String nm) {
		naam=nm ;
	}
	public String getSmaak() {
		return smaak;
	}
	public void setSmaak(String sm) {
		smaak=sm;
	}
	public double getPrijs() {
		return prijs;
	}
	public void setPrijs(double pr) {
		prijs = pr;
	}
	public double getAlcoholpercentage() {
		return alcoholpercentage;
	}
	public void setAlcoholpercentage(double ap) {
		alcoholpercentage =ap;
	}
	

}
