package nl.hu.ipass.app.webservices;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import nl.hu.ipass.app.webservices.AuthenicationResource;
import nl.hu.ipass.app.webservices.MySecurityContext;

public class AuthenticationFilter {
	public void filter(ContainerRequestContext requestCtx) throws IOException {
	    
	    boolean isSecure = requestCtx.getSecurityContext().isSecure();
	    
	    //Hier wordt gekeken of de token nog geldig is en anders wordt hij geredirect als gast.
	    MySecurityContext msc = new MySecurityContext("Unknown", "guest", isSecure);
	    String authHeader = requestCtx.getHeaderString(HttpHeaders.AUTHORIZATION);
	    if (authHeader != null && authHeader.startsWith("Bearer ")) {
	       
	      String token = authHeader.substring("Bearer".length()).trim();
	 
	      try {
	        // Kijken of de token nog geldig is en of hij wel bestaat anders gaat hij door als gast.
	        JwtParser parser = Jwts.parser().setSigningKey(AuthenicationResource.key);
	        Claims claims = parser.parseClaimsJws(token).getBody();
	 
	        String user = claims.getSubject();
	        String role = claims.get("role").toString();
	        msc = new MySecurityContext(user, role, isSecure);
	        
	      } catch (JwtException | IllegalArgumentException e) {
	        System.out.println("Invalid JWT, processing as guest!");
	      }
	    }
	    
	    requestCtx.setSecurityContext(msc);
	  }
}
