package com.bugtracker.database.parser;

import com.bugtracker.database.results.IQueryResult;
import com.bugtracker.database.results.parsed.LabelParsedQueryResult;

/** Parses a {@link LabelParsedQueryResult} from a {@link IQueryResult}. */
public class LabelsQueryResultParser<T extends IQueryResult> extends QueryResultParser<T, LabelParsedQueryResult> {

	@Override
	public LabelParsedQueryResult parseSingleRow(T result) {
		return new LabelParsedQueryResult( //
				result.getInt(1), //
				result.getString(2) //
		);
	}
}
