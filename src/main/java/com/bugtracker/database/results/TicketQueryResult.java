package com.bugtracker.database.results;

import java.sql.Timestamp;

import com.bugtracker.database.enums.ETables;

/** The Java class which represents a entry in the Ticket table. */
public class TicketQueryResult implements IQueryResult {

	/** The unique ID of a ticket */
	private final int ticketId;

	/** The unique ID of the user who created the ticket */
	private final int creatorId;

	/** The unique ID of the assignee */
	private final int assigneeId;

	/** The unique ID of the status of the ticket */
	private final int statusId;

	/** The title of the ticket */
	private final String title;

	/** The timestamp when the ticket was created */
	private final Timestamp creationTimestamp;

	/** Constructor */
	public TicketQueryResult(int ticketId, int creatorId, int assigneeId, int statusId, String title,
			Timestamp creationTimestamp) {
		this.ticketId = ticketId;
		this.creatorId = creatorId;
		this.assigneeId = assigneeId;
		this.statusId = statusId;
		this.title = title;
		this.creationTimestamp = creationTimestamp;
	}

	/**
	 * @see #ticketId
	 */
	public int getTicketId() {
		return ticketId;
	}

	/**
	 * @see #creatorId
	 */
	public int getCreatorId() {
		return creatorId;
	}

	/**
	 * @see #assigneeId
	 */
	public int getAssigneeId() {
		return assigneeId;
	}

	/**
	 * @see #statusId
	 */
	public int getStatusId() {
		return statusId;
	}

	/**
	 * @see #title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @see #creationTimestamp
	 */
	public Timestamp getCreationTimestamp() {
		return creationTimestamp;
	}

	@Override
	public ETables getTable() {
		return ETables.TICKETS;
	}
}
