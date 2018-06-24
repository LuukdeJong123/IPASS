package nl.hu.ipass.app.webservices;

public class ServiceProvider {
	private static WebshopService productService = new WebshopService();

	public static WebshopService getProductService() {
		return productService;
	}
}
