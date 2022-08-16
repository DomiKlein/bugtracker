package com.bugtracker.core.ticket;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
	public Ticket deleteTicket(@PathVariable int ticketId) {
		return ticketService.deleteTicket(ticketId);
	}
}
