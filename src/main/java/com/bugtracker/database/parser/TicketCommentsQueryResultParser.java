package com.bugtracker.database.parser;

import com.bugtracker.database.results.IQueryResult;
import com.bugtracker.database.results.parsed.TicketCommentParsedQueryResult;

/**
 * Parses a {@link TicketCommentParsedQueryResult} from a {@link IQueryResult}.
 */
public class TicketCommentsQueryResultParser extends QueryResultParser {

	@Override
	TicketCommentParsedQueryResult parseSingleRow(IQueryResult result) {
		return new TicketCommentParsedQueryResult( //
				result.getInt(1), //
				result.getInt(2), //
				result.getString(3), //
				result.getTimestamp(4) //
		);
	}
}
