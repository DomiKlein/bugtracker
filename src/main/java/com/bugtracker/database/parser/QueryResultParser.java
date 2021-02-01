package com.bugtracker.database.parser;

import java.util.HashSet;
import java.util.Set;

import com.bugtracker.database.results.IQueryResult;
import com.bugtracker.database.results.parsed.IParsedQueryResult;

/** Abstract class to handle a {@link IQueryResult} from a query. */
public abstract class QueryResultParser {

	public final <T extends IParsedQueryResult> Set<T> parseResult(IQueryResult result) {
		Set<T> results = new HashSet<>();

		while (result.next()) {
			results.add(parseSingleRow(result));
		}

		return results;
	}

	/** Returns the parsed result from the {@link IQueryResult}. */
	abstract <T extends IParsedQueryResult> T parseSingleRow(IQueryResult result);
}
