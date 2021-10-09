package com.bugtracker.database.model;

import com.bugtracker.database.core.MySQLDatabaseConnection;

public class Test {

	public static void main(String[] args) {
		MySQLDatabaseConnection database = new MySQLDatabaseConnection();
		database.start();

		User u = new User();
		u.setUsername("domi");
		u.setFirstName("Domi");
		u.setLastName("Klein");

		database.saveData(u);

		Ticket t = new Ticket();
		t.setCreator(u);
		t.setTitle("Example");
		t.setDescription("Example");

		database.saveData(t);

		database.readData(Ticket.class, "1");
		database.readDataWithConstraints(Ticket.class, Ticket.TITLE_COLUMN_NAME, "Example");
		t.setAssignee(u);
		database.updateData(t);
		database.readData(Ticket.class, "1");

		database.terminate();
	}
}
