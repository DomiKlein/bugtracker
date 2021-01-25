package com.bugtracker.database;

import com.bugtracker.database.enums.ETables;
import com.bugtracker.utils.StringUtils;

public class SQLQueryBuilder {

	private String query;

	private SQLQueryBuilder(String query) {
		this.query = query;
	}

	public SQLQueryBuilder selectAll() {
		query = query + " SELECT * ";
		return this;
	}

	public SQLQueryBuilder from(ETables table) {
		query = query + " FROM " + table;
		return this;
	}

	/** Returns the current query */
	public String build() {
		return query.trim();
	}

	/** Factory method to create an empty SQL query. */
	public static SQLQueryBuilder createEmptyQuery() {
		return new SQLQueryBuilder(StringUtils.EMPTY_STRING);
	}
}
