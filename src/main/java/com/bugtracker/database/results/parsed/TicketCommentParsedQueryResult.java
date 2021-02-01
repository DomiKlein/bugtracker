package com.bugtracker.database.results.parsed;

import java.util.Date;
import java.util.Objects;

import com.bugtracker.database.enums.ETables;

/** The Java class which represents a entry in the TicketComments table. */
public class TicketCommentParsedQueryResult implements IParsedQueryResult {

	/** The unique ID of a comment */
	private final int commentId;

	/** The unique ID of the user who created the comment */
	private final int creatorId;

	/** The content of the comment */
	private final String content;

	/** The timestamp when the comment was created */
	private final Date creationTimestamp;

	public TicketCommentParsedQueryResult(int commentId, int creatorId, String content, Date creationTimestamp) {
		this.commentId = commentId;
		this.creatorId = creatorId;
		this.content = content;
		this.creationTimestamp = creationTimestamp;
	}

	/**
	 * @see #commentId
	 */
	public int getTicketId() {
		return commentId;
	}

	/**
	 * @see #creatorId
	 */
	public int getCreatorId() {
		return creatorId;
	}

	/**
	 * @see #content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @see #creationTimestamp
	 */
	public Date getCreationTimestamp() {
		return creationTimestamp;
	}

	@Override
	public ETables getTable() {
		return ETables.TICKET_COMMENTS;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		TicketCommentParsedQueryResult that = (TicketCommentParsedQueryResult) o;
		return commentId == that.commentId && creatorId == that.creatorId && content.equals(that.content)
				&& creationTimestamp.equals(that.creationTimestamp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(commentId, creatorId, content, creationTimestamp);
	}
}
