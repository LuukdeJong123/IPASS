package nl.hu.ipass.app.webservices;
//Deze klasse haalt steeds maar 1 keer de webshopservice op. Dit komt door de static variabele zo wordt het niet elke keer opnieuw aangemaakt.
//
public class ServiceProvider {
	private static WebshopService webshopService = new WebshopService();

	public static WebshopService getProductService() {
		return webshopService;
	}
}
