package nl.hu.ipass.app.webservices;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;
//De naam, rol en of de "isSecure" goed is. 
public class MySecurityContext implements SecurityContext {
	private String name;
	private String role;
	private boolean isSecure;
	
	public MySecurityContext(String name, String role, boolean isSecure) {
		this.name = name;
		this.role = role;
	}
	
	 public Principal getUserPrincipal() {
		 return new Principal() {
		public String getName() { return name; }
	 };
	 }
	 //Er wordt gekeken of de rol admin of user is.
	 public boolean isUserInRole(String role) { return role.equals(this.role); }
	 public boolean isSecure() { return isSecure; }
	 public String getAuthenticationScheme() { return "Bearer"; }
}
