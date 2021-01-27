package com.bugtracker.database.parser;

import com.bugtracker.database.results.IQueryResult;
import com.bugtracker.database.results.parsed.TicketCommentParsedQueryResult;

/**
 * Parses a {@link TicketCommentParsedQueryResult} from a {@link IQueryResult}.
 */
public class TicketCommentsQueryResultParser<T extends IQueryResult>
		extends
			QueryResultParser<T, TicketCommentParsedQueryResult> {

	@Override
	public TicketCommentParsedQueryResult parseSingleRow(T result) {
		return new TicketCommentParsedQueryResult( //
				result.getInt(1), //
				result.getInt(2), //
				result.getString(3), //
				result.getTimestamp(4) //
		);
	}
}
