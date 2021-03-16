package com.bugtracker.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/** An example service. */
@Path("/helloworld")
public class HelloWorldService {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHelloWorld() {
		return "Hello World";
	}
}
