package com.bugtracker.database.parser;

import com.bugtracker.database.results.IQueryResult;
import com.bugtracker.database.results.parsed.TicketParsedQueryResult;

/** Parses a {@link TicketParsedQueryResult} from a {@link IQueryResult}. */
public class TicketsQueryResultParser extends QueryResultParser {

	@Override
	TicketParsedQueryResult parseSingleRow(IQueryResult result) {
		return new TicketParsedQueryResult( //
				result.getInt(1), //
				result.getInt(2), //
				result.getInt(3), //
				result.getInt(4), //
				result.getString(5), //
				result.getString(6), //
				result.getTimestamp(7) //
		);
	}
}
