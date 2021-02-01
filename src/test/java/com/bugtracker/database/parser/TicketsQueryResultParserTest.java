package com.bugtracker.database.parser;

import java.util.Date;

import org.junit.jupiter.api.Test;

import com.bugtracker.database.enums.ETables;
import com.bugtracker.database.results.parsed.TicketParsedQueryResult;

class TicketsQueryResultParserTest extends ResultParserTestBase {

	@Test
	void testParseSingleRow() {
		super.testParseSingleRow(QueryResultParserUtils.getParserForTable(ETables.TICKETS),
				new TicketParsedQueryResult(1, 1, 1, 1, "Test 1", "Test 1", new Date(1)));
	}
}