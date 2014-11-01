package com.shiftapp.ws.api;

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

import com.shiftapp.ws.authentication.IAuthenticator;
import com.shiftapp.ws.exceptions.TokenOutOfDateException;
import com.shiftapp.ws.exceptions.UnauthorizedTokenException;
import com.shiftapp.ws.model.classes.WeeklySchedule;
import com.shiftapp.ws.service.weekinfo.IWeekInfoService;

@Path("/weekinfo")
public class WeekViewAPI {

	@Autowired
	private IAuthenticator authenticator;
	
	@Autowired
	private IWeekInfoService weekInfoService;

	@Path("/business/{businessId}")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public Response getWeeklySchedule(@FormParam("token") String token, @PathParam("businessId") long businessId) throws UnauthorizedTokenException, TokenOutOfDateException {
		authenticator.authenticateToken(token);
		
		WeeklySchedule weeklySchedule = weekInfoService.getWeeklySchedule(token, businessId);
		return Response.status(200).entity(weeklySchedule).build();
	}
}
