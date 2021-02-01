package com.bugtracker.database.parser;

import com.bugtracker.database.results.IQueryResult;
import com.bugtracker.database.results.parsed.UserParsedQueryResult;

/** Parses a {@link UserParsedQueryResult} from a {@link IQueryResult}. */
public class UsersQueryResultParser extends QueryResultParser {

	@Override
	UserParsedQueryResult parseSingleRow(IQueryResult result) {
		return new UserParsedQueryResult( //
				result.getInt(1), //
				result.getString(2), //
				result.getString(3), //
				result.getString(4) //
		);
	}
}
