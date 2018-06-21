package nl.hu.ipass.app.webservices; 

import java.sql.SQLException;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import nl.hu.ipass.app.persistence.Opmerking;
import nl.hu.ipass.app.persistence.Product;

@Path("/producten")
public class ProductResource {
	@GET
	@Produces("application/json")
	public String getProducten() throws ClassNotFoundException, SQLException {
		ProductService service = ServiceProvider.getProductService();
		JsonArrayBuilder jab = Json.createArrayBuilder();{
			for(Product product : service.getAllProducten()) {
				JsonObjectBuilder job = Json.createObjectBuilder();
				job.add("ID", product.getID());
				job.add("Naam", product.getNaam());
				job.add("Smaak", product.getSmaak());
				job.add("Prijs", product.getPrijs());
				job.add("Alcoholpercentage", product.getAlcoholpercentage());
				jab.add(job);
			}
			JsonArray lijst = jab.build();
			String s = lijst.toString();
			return s;
		}
	}
	
	@GET
	@Path("/opmerkingen")
	@Produces("application/json")
	public String opmerkingen() throws ClassNotFoundException, SQLException {
		ProductService service = ServiceProvider.getProductService();
		JsonArrayBuilder jab = Json.createArrayBuilder();{
			for(Opmerking opmerking : service.getOpmerkingen()) {
				JsonObjectBuilder job = Json.createObjectBuilder();
				job.add("ID", opmerking.getID());
				job.add("Opmerking", opmerking.getTekst());
				jab.add(job);
			}
			JsonArray lijst = jab.build();
			String s = lijst.toString();
			return s;
		}
	}
	
	@GET
	@Path("/winkelwagen")
	@Produces("application/json")
	public String winkelwagen() throws ClassNotFoundException, SQLException {
		ProductService service = ServiceProvider.getProductService();
		JsonArrayBuilder jab = Json.createArrayBuilder();{
			for(Product product : service.getWinkelwagen()) {
				JsonObjectBuilder job = Json.createObjectBuilder();
				job.add("winkelwagen_id", product.getID());
				job.add("winkelwagen_naam", product.getNaam());
				job.add("winkelwagen_prijs", product.getPrijs());
				jab.add(job);
			}
			JsonArray lijst = jab.build();
			String s = lijst.toString();
			return s;
		}
	}
	

	
	
	@POST
	@RolesAllowed("ADMIN")
	@Produces("application/json")
	public Response createProduct(@FormParam("naam") String naam,@FormParam("smaak") String smaak,
								@FormParam("prijs") double prijs, @FormParam("alcholpercentage") double alcoholpercentage) throws ClassNotFoundException, SQLException {
		ProductService service = ServiceProvider.getProductService();
		Product product = new Product();
		product.setAlcoholpercentage(alcoholpercentage);
		product.setNaam(naam);
		product.setPrijs(prijs);
		product.setSmaak(smaak);
		service.createProduct(product);
		return Response.ok(product).build();
		
	}
	
	
	@PUT
	@RolesAllowed("ADMIN")
	@Produces("application/json")
	public Response createBestelling(@FormParam("tekst") String tekst,@FormParam("ID") int ID) throws ClassNotFoundException, SQLException {
			ProductService service = ServiceProvider.getProductService();
			Opmerking opmerking = new Opmerking();
			opmerking.setTekst(tekst);
			opmerking.setID(ID);
			service.createOpmerking(opmerking);
			return Response.ok(opmerking).build();
			
			}
	@POST 
	@Path("/post_winkelwagen")
	@RolesAllowed("ADMIN")
	@Produces("application/json")
	public Response inputWinkelwagen(@FormParam("naam") String naam,@FormParam("prijs") double prijs) throws ClassNotFoundException, SQLException {
		ProductService service = ServiceProvider.getProductService();
		Product product =  new Product();
		product.setNaam(naam);
		product.setPrijs(prijs);
		service.insertWinkelwagen(product);
		return Response.ok(product).build();
	}
}
