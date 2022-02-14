package com.bugtracker.database.repository;

import org.springframework.data.repository.CrudRepository;

import com.bugtracker.database.model.Ticket;

public interface TicketsRepository extends CrudRepository<Ticket, Integer> {
}
