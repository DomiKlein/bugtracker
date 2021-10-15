package com.bugtracker.database.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import com.bugtracker.database.model.constants.Tables;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Class which represent a comment on a {@link Ticket}. */
@Entity
@Table(name = Tables.TICKET_COMMENTS)
@IdClass(TicketComment.TicketCommentKey.class)
@ExportToTypeScript
public class TicketComment extends DatabaseEntity {

	/** Column name of the ticket id. */
	public static final String TICKET_COMMENT_ID_COLUMN_NAME = "commentId";
	/** Column name of the user who created this ticket. */
	public static final String CREATOR_COLUMN_NAME = "creator";
	/** Column name of the ticket that the comment belongs to. */
	public static final String TICKET_COLUMN_NAME = "ticket";
	/** Column name of the actual comment. */
	public static final String COMMENT_COLUMN_NAME = "comment";
	/** Column name of the creation timestamp. */
	public static final String CREATION_TIMESTAMP_COLUMN_NAME = "creationTimestamp";

	@Id
	@GenericGenerator(name = "depending_incrementer", strategy = "com.bugtracker.database.model.generators.TicketCommentIdGenerator")
	@GeneratedValue(generator = "depending_incrementer")
	@Column(name = TICKET_COMMENT_ID_COLUMN_NAME, nullable = false)
	@JsonProperty(TICKET_COMMENT_ID_COLUMN_NAME)
	private Integer commentId;

	@ManyToOne
	@JoinColumn(name = CREATOR_COLUMN_NAME, referencedColumnName = User.USER_ID_COLUMN_NAME, nullable = false)
	@JsonProperty(value = CREATOR_COLUMN_NAME, required = true)
	private User creator;

	@Id
	@ManyToOne
	@JoinColumn(name = TICKET_COLUMN_NAME, referencedColumnName = Ticket.TICKET_ID_COLUMN_NAME, nullable = false)
	@JsonProperty(value = TICKET_COLUMN_NAME, required = true)
	private Ticket ticket;

	@Column(name = COMMENT_COLUMN_NAME, nullable = false, columnDefinition = "mediumtext")
	@JsonProperty(value = COMMENT_COLUMN_NAME, required = true)
	private String comment;

	@CreationTimestamp
	@Column(name = CREATION_TIMESTAMP_COLUMN_NAME, nullable = false)
	@JsonProperty(CREATION_TIMESTAMP_COLUMN_NAME)
	private Date creationTimestamp;

	/**
	 * @see #creator
	 */
	public void setCreator(User creator) {
		this.creator = creator;
	}

	/**
	 * @see #ticket
	 */
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	/**
	 * @see #comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @see #commentId
	 */
	public Integer getCommentId() {
		return commentId;
	}

	/**
	 * @see #creator
	 */
	public User getCreator() {
		return creator;
	}

	/**
	 * @see #ticket
	 */
	public Ticket getTicket() {
		return ticket;
	}

	/**
	 * @see #comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @see #creationTimestamp
	 */
	public Date getCreationTimestamp() {
		return creationTimestamp;
	}

	@Override
	public Object getId() {
		return getCommentId();
	}

	/** Represents the key of the table. */
	public static class TicketCommentKey implements Serializable {
		protected Integer commentId;
		protected Ticket ticket;

		public TicketCommentKey() {
		}

		public TicketCommentKey(Integer commentId, Ticket ticket) {
			this.commentId = commentId;
			this.ticket = ticket;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			TicketCommentKey that = (TicketCommentKey) o;
			return commentId.equals(that.commentId) && ticket.equals(that.ticket);
		}

		@Override
		public int hashCode() {
			return Objects.hash(commentId, ticket);
		}

	}

	@Override
	public boolean forceMerge() {
		return true;
	}
}
