package com.bugtracker.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugtracker.database.model.Ticket;
import com.bugtracker.database.repository.TicketsRepository;

@Service
public class TicketService {

	@Autowired
	private TicketsRepository ticketsRepository;

	public List<Ticket> getAllTickets() {
		List<Ticket> tickets = new ArrayList<>();
		for (Ticket ticket : ticketsRepository.findAll()) {
			tickets.add(ticket);
		}
		return tickets;
	}
}
