package com.shiftapp.ws.api;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.shiftapp.ws.model.classes.User;
import com.shiftapp.ws.service.identification.IIdentificationService;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/identify")
public class IdentificationAPI {

	@Autowired
	private IIdentificationService identificationService;

	@Path("/lookup/countryCode/{countryCode}/phoneNumber/{phoneNumber}")
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response lookup(@PathParam("countryCode") String countryCode, @PathParam("phoneNumber") String phoneNumber) {
		User user = identificationService.lookup(countryCode, phoneNumber);
		return Response.status(200).entity(user).build();
	}
	
	@Path("/loginA")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response loginAStep(@FormParam("countryCode") String countryCode, @FormParam("phoneNumber") String phoneNumber) {
		if(!identificationService.loginAStep(countryCode, phoneNumber)) {
			return Response.status(403).entity("Login failed").build();
		}
		return Response.status(200).entity("Authentication code has been sent").build();
	}

	@Path("/loginB")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response loginBStep(@FormParam("countryCode") String countryCode, @FormParam("phoneNumber") String phoneNumber, @FormParam("password") String password) {
		if(!identificationService.loginBStep(countryCode, phoneNumber, password)) {
			return Response.status(403).entity("Login failed").build();
		}
		return Response.status(200).entity("Loged in successfuly").build();
	}
	
	@Path("/signup")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response signup(@FormDataParam("firstName") String firstName, @FormDataParam("lastName") String lastName, @FormDataParam("countryCode") String countryCode, @FormDataParam("phoneNumber") String phoneNumber, @FormDataParam("picFile") InputStream uploadedPicInputStream,
			@FormDataParam("picFile") FormDataContentDisposition picFileDetail) {
		if(!identificationService.signup(firstName, lastName, countryCode, phoneNumber, uploadedPicInputStream, picFileDetail)) {
			return Response.status(403).entity("Signup failed").build();
		}
		return Response.status(200).entity("Singed up successfuly").build();
	}

}
