package com.bugtracker.database.results;

import java.awt.*;
import java.util.Date;

/** The result of an query to a database. */
public interface IQueryResult {

	/**
	 * Returns whether there is another row after the current one. Advances the
	 * counter.
	 */
	boolean next();

	/** Returns the {@link Integer} saved in the given {@code columnIndex}. */
	Integer getInt(int columnIndex);

	/** Returns the {@link String} saved in the given {@code columnIndex}. */
	String getString(int columnIndex);

	/** Returns the {@link Date} saved in the given {@code columnIndex}. */
	Date getTimestamp(int columnIndex);

	/** Returns the {@link Color} saved in the given {@code columnIndex}. */
	Color getColor(int columnIndex);
}
