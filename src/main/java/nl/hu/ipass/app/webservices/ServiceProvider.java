package nl.hu.ipass.app.webservices;

public class ServiceProvider {
	private static ProductService productService = new ProductService();

	public static ProductService getProductService() {
		return productService;
	}
}
