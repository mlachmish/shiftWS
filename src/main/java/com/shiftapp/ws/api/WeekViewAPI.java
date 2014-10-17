package com.shiftapp.ws.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/weekview")
public class WeekViewAPI {

	@GET
	@Produces({"application/json"})
	public Response getWeekSchedule(@QueryParam("token") String token) {
		 
		String output = "shift ws say : " + token;
 
		return Response.status(200).entity(output).build();
 
	}
}
