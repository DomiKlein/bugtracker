package com.bugtracker.database.parser;

import com.bugtracker.database.results.IQueryResult;
import com.bugtracker.database.results.parsed.TicketToLabelParsedQueryResult;

/**
 * Parses a {@link TicketToLabelParsedQueryResult} from a {@link IQueryResult}.
 */
public class TicketToLabelsQueryResultParser<T extends IQueryResult>
		extends
			QueryResultParser<T, TicketToLabelParsedQueryResult> {

	@Override
	public TicketToLabelParsedQueryResult parseSingleRow(T result) {
		return new TicketToLabelParsedQueryResult( //
				result.getInt(1), //
				result.getInt(2) //
		);
	}
}
