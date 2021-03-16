package com.bugtracker.database.results.parsed;

import java.util.Objects;

import com.bugtracker.database.enums.ETables;

/** The Java class which represents a entry in the Workflow table. */
public class WorkflowParsedQueryResult implements IParsedQueryResult {

	/** The unique ID of the status */
	private final int statusId;

	/** The unique ID of the next possible status */
	private final int nextStatusId;

	public WorkflowParsedQueryResult(int statusId, int nextStatusId) {
		this.statusId = statusId;
		this.nextStatusId = nextStatusId;
	}

	/**
	 * @see #statusId
	 */
	public int getStatusId() {
		return statusId;
	}

	/**
	 * @see #nextStatusId
	 */
	public int getNextStatusId() {
		return nextStatusId;
	}

	@Override
	public ETables getTable() {
		return ETables.WORKFLOW;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		WorkflowParsedQueryResult that = (WorkflowParsedQueryResult) o;
		return statusId == that.statusId && nextStatusId == that.nextStatusId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(statusId, nextStatusId);
	}
}
