package com.bugtracker.database.parser;

import java.sql.ResultSet;

import com.bugtracker.database.results.QueryResult;

/** Abstract class to handle a {@link ResultSet} from a SQL query. */
public interface QueryResultParser<T extends QueryResult> {

	/** Parses the result from the given {@link ResultSet}. */
	public T parseInfoFromResult(ResultSet resultSet);
}
