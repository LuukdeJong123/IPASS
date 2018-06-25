package nl.hu.ipass.app.webservices; 

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import nl.hu.ipass.app.persistence.Opmerking;
import nl.hu.ipass.app.persistence.Product;
import nl.hu.ipass.app.persistence.User;

//Dit is de klasse die communiceert met de front 
//Ik begin met het path producten waar draait het de hele webshop om
@Path("/producten")
public class WebshopResource {
	//Deze get haalt alle producten uit de database en zet ze in de tabel van de pagina producten bekijken.
	@GET
	@Produces("application/json")
	public String getProducten() throws ClassNotFoundException, SQLException {
		WebshopService service = ServiceProvider.getProductService();
		//Hier wordt de Service opgehaald met alle functies van het persistence mapje dit geld hetzelfde voor alle andere @GET'S!!!.
		JsonArrayBuilder jab = Json.createArrayBuilder();{
			//Er wordt nu een jsonarray aangemaakt en daar wordt alle informatie van het product ingezet dit geld hetzelfde voor alle andere @GET'S!!!. 
			for(Product product : service.getAllProducten()) {
				JsonObjectBuilder job = Json.createObjectBuilder();
				job.add("ID", product.getID());
				job.add("Naam", product.getNaam());
				job.add("Smaak", product.getSmaak());
				job.add("Prijs", product.getPrijs());
				job.add("Alcoholpercentage", product.getAlcoholpercentage());
				jab.add(job);
			}
			//De jsonarray wordt hier een naar eens string toegezet zodat hij gereturnd kan worden dit geld hetzelfde voor alle andere @GET'S!!!
			JsonArray lijst = jab.build();
			String s = lijst.toString();
			return s;
		}
	}
	//Deze get haalt alle opmerking uit de database.
	@GET
	@Path("/opmerkingen")
	@Produces("application/json")
	public String opmerkingen() throws ClassNotFoundException, SQLException {
		WebshopService service = ServiceProvider.getProductService();
		//Hier wordt de Service opgehaald met alle functies van het persistence mapje.
		JsonArrayBuilder jab = Json.createArrayBuilder();{
			//Er wordt nu een jsonarray aangemaakt en daar wordt alle informatie van de opmerking ingezet. 
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
	//Deze get haalt alle winkelwagen items uit de database.
	@GET
	@Path("/winkelwagen")
	@Produces("application/json")
	public String winkelwagen() throws ClassNotFoundException, SQLException {
		WebshopService service = ServiceProvider.getProductService();
		JsonArrayBuilder jab = Json.createArrayBuilder();{
			//Er wordt nu een jsonarray aangemaakt en daar wordt alle informatie van de winkelwagen item ingezet.
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
	//Deze get haalt alle user items uit de database.
	@GET
	@Path("/user")
	@Produces("application/json")
	public String klant() throws ClassNotFoundException, SQLException {
		WebshopService service = ServiceProvider.getProductService();
		JsonArrayBuilder jab = Json.createArrayBuilder();{
			for(User user : service.getID()) {
				JsonObjectBuilder job = Json.createObjectBuilder();
				job.add("user_id", user.getID());
				job.add("user_naam", user.getGebruikersnaam());
				jab.add(job);
			}
			JsonArray lijst = jab.build();
			String s = lijst.toString();
			return s;
		}
	}
	

	
	//Deze post voegt een nieuw product toe aan de database
	@POST
//	@RolesAllowed("ADMIN")
	@Produces("application/json")
	public Response createProduct(@FormParam("naam") String naam,@FormParam("smaak") String smaak,
								@FormParam("prijs") double prijs, @FormParam("alcholpercentage") double alcoholpercentage) throws ClassNotFoundException, SQLException {
		WebshopService service = ServiceProvider.getProductService();
		//Er wordt een nieuw product aangemaakt en wordt geset met de data is meekregen van de front end
		Product product = new Product();
		product.setAlcoholpercentage(alcoholpercentage);
		product.setNaam(naam);
		product.setPrijs(prijs);
		product.setSmaak(smaak);
		//Dan wordt dat product naar de perstince gestuurd en kan hij aan de database toegevoegd worden
		service.createProduct(product);
		return Response.ok(product).build();
		
	}
	
	//Deze put voegt een nieuw bestelling toe aan de database
	@PUT
//	@RolesAllowed("ADMIN")
	@Produces("application/json")
	public Response createOpmerking(@FormParam("tekst") String tekst,@FormParam("ID") int ID) throws ClassNotFoundException, SQLException {
		
			WebshopService service = ServiceProvider.getProductService();
			//Er wordt een nieuw opmerking aangemaakt en wordt geset met de data is meekregen van de front end
			Opmerking opmerking = new Opmerking();
			opmerking.setTekst(tekst);
			opmerking.setID(ID);
			service.createOpmerking(opmerking);
			return Response.ok(opmerking).build();
			
			}
	//Deze put voegt een nieuw winkelwagen item toe aan de database
	@POST 
	@Path("/post_winkelwagen")
//	@RolesAllowed("ADMIN")
	@Produces("application/json")
	public Response inputWinkelwagen(@FormParam("productnaam") String naam,@FormParam("productprijs") double prijs) throws ClassNotFoundException, SQLException {
		WebshopService service = ServiceProvider.getProductService();
		//Er wordt een nieuw product aangemaakt en wordt geset met de data is meekregen van de front end
		//De winkelwagen heeft minder data nodig dan een echt product omdat in de winkelwagen alleen de naam en prijs bekend is
		Product product =  new Product();
		product.setNaam(naam);
		product.setPrijs(prijs);
		service.insertWinkelwagen(product);
		return Response.ok(product).build();
	}
	//Deze delete verwijderd een winkelwagen item uit database
	@DELETE	
//	@RolesAllowed("USER")
	@Path("/{winkelwagen_id}")
    @Produces("application/json")
    public Response deleteWinkelwagen(@PathParam("winkelwagen_id") int code) throws ClassNotFoundException, SQLException {
		WebshopService service = ServiceProvider.getProductService();
		//Hij geeft de winkelwagen id mee en kan dan zo het item verwijderen uit de winkelwagen
        service.deleteWinkelwagen(code);
        return Response.ok().build();
 
    }
	//Deze post voegt een nieuw bestelling toe aan de database
	@POST 
	@Path("/post_bestelling")
//	@RolesAllowed("ADMIN")
	@Produces("application/json")
	public Response inputBestelling(@FormParam("datum") String datum,@FormParam("tijd") String tijd, @FormParam("id") int id) throws ClassNotFoundException, SQLException {
		WebshopService service = ServiceProvider.getProductService();
		//Hij geeft de datum, tijd en klantid mee met de bestelling.
		service.insertBestelling(datum, tijd, id);
		String var= "gelukt!";
		return Response.ok(var).build();
	}
}
