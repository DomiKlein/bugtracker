package com.bugtracker.services;


import com.bugtracker.services.utils.JsonString;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/info")
public class HelloWorldService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonString sayHelloWorld() {
        return new JsonString("Hello World");
    }
}
