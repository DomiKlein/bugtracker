package com.bugtracker.database.enums;

public enum Tables {
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
	private Tables(String name) {
		this.name = name;
	}
}
