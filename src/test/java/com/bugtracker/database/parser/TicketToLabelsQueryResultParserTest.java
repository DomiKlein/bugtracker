package com.bugtracker.database.parser;

import org.junit.jupiter.api.Test;

import com.bugtracker.database.results.parsed.TicketToLabelParsedQueryResult;

/** Tests for the {@link TicketToLabelsQueryResultParser} */
class TicketToLabelsQueryResultParserTest extends ResultParserTestBase {

	@Test
	void testParseSingleRow() {
		super.testParseSingleRow(new TicketToLabelsQueryResultParser(), new TicketToLabelParsedQueryResult(1, 1));
	}
}