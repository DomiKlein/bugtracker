package com.bugtracker.database.parser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.bugtracker.database.results.IQueryResult;

/** Abstract class to handle a {@link ResultSet} from a SQL query. */
public abstract class QueryResultParser<T extends IQueryResult> {

	public Set<T> parseResultSet(ResultSet resultSet) throws SQLException {
		Set<T> results = new HashSet<>();

		while (resultSet.next()) {
			results.add(parseSingleRow(resultSet));
		}

		return results;
	}

	/** Returns the parsed result from the {@link ResultSet}. */
	public abstract T parseSingleRow(ResultSet resultSet) throws SQLException;
}
