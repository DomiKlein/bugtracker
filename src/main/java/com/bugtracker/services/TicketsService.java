package com.bugtracker.services;

import java.util.List;

import com.bugtracker.database.model.Ticket;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/tickets")
public class TicketsService {

	@GET
	public List<Ticket> getAllTickets() {
		Ticket t = new Ticket();
		t.setTitle("Example");
		return List.of(t);
	}

}
