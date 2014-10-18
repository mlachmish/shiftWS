package com.shiftapp.ws.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@QueryParam("countryCode") String countryCode, @QueryParam("phoneNumber") String phoneNumber, @QueryParam("password") String password) {
		if(!signUpService.login(countryCode, phoneNumber, password)) {
			return Response.status(403).entity("Login failed").build();
		}
		return Response.status(200).entity("loged in successfuly").build();
	}

}
