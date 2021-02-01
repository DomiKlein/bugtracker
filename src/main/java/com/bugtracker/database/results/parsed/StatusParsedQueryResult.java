package com.bugtracker.database.results.parsed;

import java.awt.*;
import java.util.Objects;

import com.bugtracker.database.enums.ETables;

/** The Java class which represents a entry in the Status table. */
public class StatusParsedQueryResult implements IParsedQueryResult {

	/** The unique ID of a status */
	private final int statusId;

	/** The name of the status */
	private final String statusName;

	/** The color of the status */
	private final Color color;

	public StatusParsedQueryResult(int statusId, String statusName, Color color) {
		this.statusId = statusId;
		this.statusName = statusName;
		this.color = color;
	}

	/**
	 * @see #statusId
	 */
	public int getStatusId() {
		return statusId;
	}

	/**
	 * @see #statusName
	 */
	public String getStatusName() {
		return statusName;
	}

	/**
	 * @see #color
	 */
	public Color getColor() {
		return color;
	}

	@Override
	public ETables getTable() {
		return ETables.STATUS;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		StatusParsedQueryResult that = (StatusParsedQueryResult) o;
		return statusId == that.statusId && statusName.equals(that.statusName) && color.equals(that.color);
	}

	@Override
	public int hashCode() {
		return Objects.hash(statusId, statusName, color);
	}
}
