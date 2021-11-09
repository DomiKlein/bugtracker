package com.bugtracker.services;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/info")
public class HelloWorldService {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response sayHelloWorld() {
		return Response.status(Response.Status.OK).entity("Hello World!").build();
	}
}
