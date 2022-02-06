package com.bugtracker.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bugtracker.database.model.Ticket;
import com.bugtracker.services.TicketService;

@Controller
@RequestMapping(path = "/api/tickets")
public class TicketsController {
	@Autowired
	private TicketService ticketService;

	@ResponseBody
	@GetMapping
	public List<Ticket> getAllTickets() {
		return ticketService.getAllTickets();
	}

	@ResponseBody
	@PostMapping
	public Ticket createTicket(@RequestBody Ticket ticket) {
		return ticketService.createTicket(ticket);
	}

	@ResponseBody
	@PutMapping
	public Ticket updateTicket(@RequestBody Ticket ticket) {
		return ticketService.updateTicket(ticket);
	}

	@ResponseBody
	@DeleteMapping(path = "/{ticketId}")
	public Ticket deleteTicket(@PathParam("ticketId") int ticketId) {
		return ticketService.deleteTicket(ticketId);
	}
}
