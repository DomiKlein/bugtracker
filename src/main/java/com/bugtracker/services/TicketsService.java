package com.bugtracker.services;

import java.util.List;

import com.bugtracker.database.core.MySQLDatabaseConnection;
import com.bugtracker.database.model.Ticket;
import com.bugtracker.database.model.User;
import com.bugtracker.init.BugtrackerInstance;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/tickets")
public class TicketsService {

	@GET
	public List<Ticket> getAllTickets() {
		MySQLDatabaseConnection db = BugtrackerInstance.getInstance().getDatabaseConnectionThread();
		User u = db.readData(User.class, 1);
		if (u == null) {
			u = new User("domi", "Domi", "Klein");
			db.saveData(u);
		}

		int available = db.readTable(Ticket.class).size();
		Ticket t = new Ticket(u, "Ticket " + available, "Description");
		db.saveData(t);

		return db.readTable(Ticket.class);
	}

}
