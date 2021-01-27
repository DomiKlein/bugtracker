package com.bugtracker.database.results.parsed;

import com.bugtracker.database.enums.ETables;

/** The Java class which represents a entry in the Workflow table. */
public class WorkflowParsedQueryResult implements IParsedQueryResult {

	/** The unique ID of a status */
	private final int statusId;

	/** The unique ID of a next possible status */
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
}
