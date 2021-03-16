package com.bugtracker.database.results.parsed;

import com.bugtracker.database.enums.ETables;

/**
 * Represents a {@link com.bugtracker.database.results.IQueryResult} which is
 * already parsed into a java class.
 */
public interface IParsedQueryResult {
	ETables getTable();
}
