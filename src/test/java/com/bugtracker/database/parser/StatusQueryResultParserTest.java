package com.bugtracker.database.parser;

import java.awt.*;

import org.junit.jupiter.api.Test;

import com.bugtracker.database.results.parsed.StatusParsedQueryResult;

/** Tests for the {@link StatusQueryResultParser} */
class StatusQueryResultParserTest extends ResultParserTestBase {

	@Test
	void testParseSingleRow() {
		super.testParseSingleRow(new StatusQueryResultParser(), new StatusParsedQueryResult(1, "Test 1", new Color(1)));
	}
}