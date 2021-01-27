package com.bugtracker.database.parser;

import com.bugtracker.database.results.IQueryResult;
import com.bugtracker.database.results.parsed.StatusParsedQueryResult;

/** Parses a {@link StatusParsedQueryResult} from a {@link IQueryResult}. */
public class StatusQueryResultParser<T extends IQueryResult> extends QueryResultParser<T, StatusParsedQueryResult> {

	@Override
	public StatusParsedQueryResult parseSingleRow(T result) {
		return new StatusParsedQueryResult( //
				result.getInt(1), //
				result.getString(2), //
				result.getColor(3) //
		);
	}
}
