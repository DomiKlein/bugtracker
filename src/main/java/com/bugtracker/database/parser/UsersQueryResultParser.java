package com.bugtracker.database.parser;

import com.bugtracker.database.results.IQueryResult;
import com.bugtracker.database.results.parsed.UserParsedQueryResult;

/** Parses a {@link UserParsedQueryResult} from a {@link IQueryResult}. */
public class UsersQueryResultParser<T extends IQueryResult> extends QueryResultParser<T, UserParsedQueryResult> {

	@Override
	public UserParsedQueryResult parseSingleRow(T result) {
		return new UserParsedQueryResult( //
				result.getInt(1), //
				result.getString(2), //
				result.getString(3), //
				result.getString(4) //
		);
	}
}
