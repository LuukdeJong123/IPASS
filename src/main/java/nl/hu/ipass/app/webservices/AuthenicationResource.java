package nl.hu.ipass.app.webservices;

import java.security.Key;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import nl.hu.ipass.app.persistence.UserDao;
import nl.hu.ipass.app.persistence.UserPostgresDaoImpl;

@Path("/authentication")
public class AuthenicationResource {
	final static public Key key = MacProvider.generateKey();

	  @POST
	  @Produces(MediaType.APPLICATION_JSON)
	  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	  public Response authenticateUser(@FormParam("username") String username, 
	                                   @FormParam("password") String password) throws ClassNotFoundException, SQLException {
	    try {
	      // Authenticate the user against the database
	      UserDao dao = new UserPostgresDaoImpl();
	      String role = dao.findRoleForUser(username, password);
	      
	      if (role == null) { throw new IllegalArgumentException("No user found!");  } 
	      
	      String token = createToken(username, role);

	      SimpleEntry<String, String> JWT = new SimpleEntry<String, String>("JWT", token);
	      return Response.ok(JWT).build();


	    } catch (JwtException | IllegalArgumentException e) 
	        { return Response.status(Response.Status.UNAUTHORIZED).build(); }
	  }
	  private String createToken(String username, String role) throws JwtException {
		    Calendar expiration = Calendar.getInstance();
		    expiration.add(Calendar.MINUTE, 30);
		  
		    return Jwts.builder()
		      .setSubject(username)
		      .setExpiration(expiration.getTime())
		      .claim("role", role)
		      .signWith(SignatureAlgorithm.HS512, key)
		      .compact();
	  }
	  

	  @PUT
	  @Produces("application/json")
	  public Response addAccount(@FormParam ("voornaam")String voornaam, @FormParam("tussenvoegsel")String tussenvoegsel,
			  						@FormParam("achternaam") String achternaam, @FormParam("gebruikersnaam") String gebruikersnaam ,
			  						@FormParam("wachtwoord") String wachtwoord) throws ClassNotFoundException, SQLException {
		  UserDao dao = new UserPostgresDaoImpl();
		  String var= "hoi";
		  dao.add_account(voornaam, tussenvoegsel, achternaam, gebruikersnaam, wachtwoord);
		  if (voornaam == null) {
	            Map<String, String> messages = new HashMap<String, String>();
	            messages.put("error", "Country does not exist!");
	            return Response.status(409).entity(messages).build();
	        }
		  return Response.ok(var).build();
	  }
	  
	  
}
