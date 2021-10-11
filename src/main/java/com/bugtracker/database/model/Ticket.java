package com.bugtracker.database.model;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

/** Class which represents a ticket */
@Entity
@Table(name = "tickets")
public class Ticket {

	/** Column name of the ticket id. */
	public static final String TICKET_ID_COLUMN_NAME = "ticketId";
	/** Column name of the user who created this ticket. */
	public static final String CREATOR_COLUMN_NAME = "creator";
	/**
	 * Column name of the user to whom the ticket is assigned created this ticket.
	 */
	public static final String ASSIGNEE_COLUMN_NAME = "assignee";
	/** Column name of title. */
	public static final String TITLE_COLUMN_NAME = "title";
	/** Column name of description. */
	public static final String DESCRIPTION_COLUMN_NAME = "description";
	/** Column name of the creation date. */
	public static final String CREATION_DATE_COLUMN_NAME = "creationTimestamp";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = TICKET_ID_COLUMN_NAME, nullable = false)
	private int ticketId;

	@ManyToOne
	@JoinColumn(name = CREATOR_COLUMN_NAME, referencedColumnName = User.USER_ID_COLUMN_NAME, nullable = false)
	private User creator;

	@ManyToOne
	@JoinColumn(name = ASSIGNEE_COLUMN_NAME, referencedColumnName = User.USER_ID_COLUMN_NAME)
	private User assignee;

	@Column(name = TITLE_COLUMN_NAME, nullable = false)
	private String title;

	@Column(name = DESCRIPTION_COLUMN_NAME, nullable = false)
	private String description;

	@CreationTimestamp
	@Column(name = CREATION_DATE_COLUMN_NAME, nullable = false)
	private Date creationTimestamp;

	/**
	 * @see #creator
	 */
	public void setCreator(User creator) {
		this.creator = creator;
	}

	/**
	 * @see #assignee
	 */
	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}

	/**
	 * @see #title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @see #description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @see #ticketId
	 */
	public int getTicketId() {
		return ticketId;
	}

	/**
	 * @see #creator
	 */
	public User getCreator() {
		return creator;
	}

	/**
	 * @see #assignee
	 */
	public User getAssignee() {
		return assignee;
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
}
