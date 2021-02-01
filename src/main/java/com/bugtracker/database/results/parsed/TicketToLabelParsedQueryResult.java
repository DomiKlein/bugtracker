package com.bugtracker.database.results.parsed;

import java.util.Objects;

import com.bugtracker.database.enums.ETables;

/** The Java class which represents a entry in the TicketToLabels table. */
public class TicketToLabelParsedQueryResult implements IParsedQueryResult {

	/** The unique ID of a ticket */
	private final int ticketId;

	/** The unique ID of a label which is assigned to the ticket */
	private final int labelId;

	public TicketToLabelParsedQueryResult(int ticketId, int labelId) {
		this.ticketId = ticketId;
		this.labelId = labelId;
	}

	/**
	 * @see #ticketId
	 */
	public int getTicketId() {
		return ticketId;
	}

	/**
	 * @see #labelId
	 */
	public int getLabelId() {
		return labelId;
	}

	@Override
	public ETables getTable() {
		return ETables.TICKET_TO_LABELS;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		TicketToLabelParsedQueryResult that = (TicketToLabelParsedQueryResult) o;
		return ticketId == that.ticketId && labelId == that.labelId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ticketId, labelId);
	}
}
