package com.bugtracker.database.parser;

import org.junit.jupiter.api.Test;

import com.bugtracker.database.results.parsed.UserParsedQueryResult;

/** Tests for the {@link UsersQueryResultParser} */
class UsersQueryResultParserTest extends ResultParserTestBase {

	@Test
	void testParseSingleRow() {
		super.testParseSingleRow(new UsersQueryResultParser(),
				new UserParsedQueryResult(1, "Test 1", "Test 1", "Test 1"));
	}
}