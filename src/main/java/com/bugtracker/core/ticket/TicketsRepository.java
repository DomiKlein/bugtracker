package com.bugtracker.core.ticket;

import org.springframework.data.repository.CrudRepository;

public interface TicketsRepository extends CrudRepository<Ticket, Integer> {
}
