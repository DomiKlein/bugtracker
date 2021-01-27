package com.bugtracker.database.parser;

import com.bugtracker.database.results.IQueryResult;
import com.bugtracker.database.results.parsed.WorkflowParsedQueryResult;

/** Parses a {@link WorkflowParsedQueryResult} from a {@link IQueryResult}. */
public class WorkflowQueryResultParser<T extends IQueryResult> extends QueryResultParser<T, WorkflowParsedQueryResult> {

	@Override
	public WorkflowParsedQueryResult parseSingleRow(T result) {
		return new WorkflowParsedQueryResult( //
				result.getInt(1), //
				result.getInt(2) //
		);
	}
}
