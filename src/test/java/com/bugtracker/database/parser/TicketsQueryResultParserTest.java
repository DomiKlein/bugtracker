package com.bugtracker.database.parser;

import java.util.Date;

import org.junit.jupiter.api.Test;

import com.bugtracker.database.results.parsed.TicketParsedQueryResult;

/** Tests for the {@link TicketsQueryResultParser} */
class TicketsQueryResultParserTest extends ResultParserTestBase {

	@Test
	void testParseSingleRow() {
		super.testParseSingleRow(new TicketsQueryResultParser(),
				new TicketParsedQueryResult(1, 1, 1, 1, "Test 1", "Test 1", new Date(1)));
	}
}