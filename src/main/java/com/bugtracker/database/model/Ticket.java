package com.bugtracker.database.model;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import com.bugtracker.database.model.util.ExportToTypeScript;
import com.bugtracker.database.model.util.Tables;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Class which represents a ticket */
@Entity
@Table(name = Tables.TICKETS)
@ExportToTypeScript
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
	/** Column name of the creation timestamp. */
	public static final String CREATION_TIMESTAMP_COLUMN_NAME = "creationTimestamp";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = TICKET_ID_COLUMN_NAME, nullable = false)
	@JsonProperty(TICKET_ID_COLUMN_NAME)
	private Integer ticketId;

	@ManyToOne
	@JoinColumn(name = CREATOR_COLUMN_NAME, referencedColumnName = User.USER_ID_COLUMN_NAME, nullable = false)
	@JsonProperty(value = CREATOR_COLUMN_NAME, required = true)
	private User creator;

	@ManyToOne
	@JoinColumn(name = ASSIGNEE_COLUMN_NAME, referencedColumnName = User.USER_ID_COLUMN_NAME)
	@JsonProperty(ASSIGNEE_COLUMN_NAME)
	private User assignee;

	@Column(name = TITLE_COLUMN_NAME, nullable = false)
	@JsonProperty(value = TITLE_COLUMN_NAME, required = true)
	private String title;

	@Column(name = DESCRIPTION_COLUMN_NAME, nullable = false, columnDefinition = "mediumtext")
	@JsonProperty(value = DESCRIPTION_COLUMN_NAME, required = true)
	private String description;

	@CreationTimestamp
	@Column(name = CREATION_TIMESTAMP_COLUMN_NAME, nullable = false)
	@JsonProperty(CREATION_TIMESTAMP_COLUMN_NAME)
	private Date creationTimestamp;

	protected Ticket() {
		// Required for fetching entries form database
	}

	public Ticket(User creator, String title, String description) {
		this.creator = creator;
		this.title = title;
		this.description = description;
	}

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
	public Integer getTicketId() {
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
