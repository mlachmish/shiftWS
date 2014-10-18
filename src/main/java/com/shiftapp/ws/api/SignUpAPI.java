package com.shiftapp.ws.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.shiftapp.ws.service.ISignUpService;

@Path("/signup")
public class SignUpAPI {

	@Autowired
	private ISignUpService signUpService;

	@Path("/lookup")
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response lookup(@QueryParam("countryCode") String countryCode, @QueryParam("phoneNumber") String phoneNumber) {
		boolean result = signUpService.lookup(countryCode, phoneNumber);
		return Response.status(200).entity(result).build();
	}
	
	@Path("/login")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public Response login(@FormParam("countryCode") String countryCode, @FormParam("phoneNumber") String phoneNumber, @FormParam("password") String password) {
		if(!signUpService.login(countryCode, phoneNumber, password)) {
			return Response.status(403).entity("Login failed").build();
		}
		return Response.status(200).entity("Loged in successfuly").build();
	}
	
	@Path("/signup")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public Response signup(@FormParam("firstName") String firstName, @FormParam("lastName") String lastName, @FormParam("countryCode") String countryCode, @FormParam("phoneNumber") String phoneNumber, @FormParam("pic") String pic) {
		if(!signUpService.signup(firstName, lastName, countryCode, phoneNumber, pic)) {
			return Response.status(403).entity("Signup failed").build();
		}
		return Response.status(200).entity("Singed up successfuly").build();
	}

}
