package com.bugtracker.api.tickets;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugtracker.core.ticket.Ticket;
import com.bugtracker.core.ticket.TicketsRepository;

/** Services all around tickets. */
@Service
public class TicketService {

	@Autowired
	private TicketsRepository ticketsRepository;

	/** Returns all stored tickets. */
	public List<Ticket> getAllTickets() {
		List<Ticket> tickets = new ArrayList<>();
		for (Ticket ticket : ticketsRepository.findAll()) {
			tickets.add(ticket);
		}
		return tickets;
	}

	/** Creates a ticket. */
	public Ticket createTicket(Ticket ticket) {
		return ticketsRepository.save(ticket);
	}

	/** Updates a ticket. */
	public Ticket updateTicket(Ticket ticket) {
		Optional<Ticket> t = ticketsRepository.findById(ticket.getTicketId());
		if (t.isEmpty()) {
			return null;
		}

		Ticket storedTicket = t.get();
		if (ticket.getTitle() != null) {
			storedTicket.setTitle(ticket.getTitle());
		}

		if (ticket.getDescription() != null) {
			storedTicket.setDescription(ticket.getDescription());
		}

		if (ticket.getAssignee() != null) {
			storedTicket.setAssignee(ticket.getAssignee());
		}

		return ticketsRepository.save(storedTicket);
	}

	/** Deletes the ticket with the given id, if exists, and returns it. */
	public Ticket deleteTicket(int ticketId) {
		Optional<Ticket> t = ticketsRepository.findById(ticketId);
		if (t.isEmpty()) {
			return null;
		}
		Ticket storedTicket = t.get();
		ticketsRepository.delete(storedTicket);
		return storedTicket;
	}
}
