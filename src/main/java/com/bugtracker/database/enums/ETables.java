package com.bugtracker.database.enums;

public enum ETables {
	USERS("Users"), //
	STATUS("Status"), //
	LABELS("Labels"), //
	TICKETS("Tickets"), //
	TICKET_COMMENTS("TicketComments"), //
	WORKFLOW("Workflow"), //
	TICKET_TO_LABELS("TicketToLabels");

	/** The real column name. */
	public final String name;

	/** Constructor used to save values of enum. */
	ETables(String name) {
		this.name = name;
	}
}
