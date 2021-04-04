package com.bugtracker.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/info")
public class HelloWorldService {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response sayHelloWorld() {
		return Response.status(Response.Status.OK).entity("Hello World!").build();
	}
}
