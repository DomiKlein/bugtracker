package com.bugtracker.database.results.parsed;

import java.util.Date;

import com.bugtracker.database.enums.ETables;

/** The Java class which represents a entry in the Tickets table. */
public class TicketParsedQueryResult implements IParsedQueryResult {

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

	/** The description of the ticket */
	private final String description;

	/** The timestamp when the ticket was created */
	private final Date creationTimestamp;

	public TicketParsedQueryResult(int ticketId, int creatorId, int assigneeId, int statusId, String title,
			String description, Date creationTimestamp) {
		this.ticketId = ticketId;
		this.creatorId = creatorId;
		this.assigneeId = assigneeId;
		this.statusId = statusId;
		this.title = title;
		this.description = description;
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
	 * @see #description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @see #creationTimestamp
	 */
	public Date getCreationTimestamp() {
		return creationTimestamp;
	}

	@Override
	public ETables getTable() {
		return ETables.TICKETS;
	}
}
