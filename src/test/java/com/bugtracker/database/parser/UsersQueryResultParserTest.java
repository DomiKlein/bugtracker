package com.bugtracker.database.parser;

import org.junit.jupiter.api.Test;

import com.bugtracker.database.enums.ETables;
import com.bugtracker.database.results.parsed.UserParsedQueryResult;

class UsersQueryResultParserTest extends ResultParserTestBase {

	@Test
	void testParseSingleRow() {
		super.testParseSingleRow(QueryResultParserUtils.getParserForTable(ETables.USERS),
				new UserParsedQueryResult(1, "Test 1", "Test 1", "Test 1"));
	}
}