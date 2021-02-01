package com.bugtracker.database.results.parsed;

import java.util.Objects;

import com.bugtracker.database.enums.ETables;

/** The Java class which represents a entry in the Labels table. */
public class LabelParsedQueryResult implements IParsedQueryResult {

	/** The unique ID of a label */
	private final int labelId;

	/** The name of the label */
	private final String labelName;

	public LabelParsedQueryResult(int labelId, String labelName) {
		this.labelId = labelId;
		this.labelName = labelName;
	}

	/**
	 * @see #labelId
	 */
	public int getStatusId() {
		return labelId;
	}

	/**
	 * @see #labelName
	 */
	public String getLabelName() {
		return labelName;
	}

	@Override
	public ETables getTable() {
		return ETables.LABELS;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		LabelParsedQueryResult that = (LabelParsedQueryResult) o;
		return labelId == that.labelId && labelName.equals(that.labelName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(labelId, labelName);
	}
}
