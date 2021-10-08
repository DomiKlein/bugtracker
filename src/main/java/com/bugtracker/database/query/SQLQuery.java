package com.bugtracker.database.query;

import java.util.ArrayList;
import java.util.List;

import com.bugtracker.database.enums.ETables;
import com.bugtracker.utils.StringUtils;

/** */
public class SQLQuery {

	/** Table which should be queried */
	private ETables tableToQuery;

	/** Entries of the table which should be queried */
	private List<String> entriesToQuery;

	public SQLQuery selectAll() {
		entriesToQuery = new ArrayList<>();
		return this;
	}

	public SQLQuery from(ETables table) {
		tableToQuery = table;
		return this;
	}

	/** Returns the current query */
	public String build() {
		String selectedEntries = "*";
		if (entriesToQuery != null && !entriesToQuery.isEmpty()) {
			selectedEntries = StringUtils.concat(", ", entriesToQuery).trim();
		}
		return "SELECT " + selectedEntries + " FROM " + tableToQuery;
	}

	/** @see #entriesToQuery */
	public List<String> getEntriesToQuery() {
		return entriesToQuery;
	}

	/** @see #tableToQuery */
	public ETables getTableToQuery() {
		return tableToQuery;
	}

	/** Factory method to create an empty SQL query. */
	public static SQLQuery createEmptyQuery() {
		return new SQLQuery();
	}
}
