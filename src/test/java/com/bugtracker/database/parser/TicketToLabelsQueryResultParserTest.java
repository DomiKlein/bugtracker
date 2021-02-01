package com.bugtracker.database.parser;

import org.junit.jupiter.api.Test;

import com.bugtracker.database.enums.ETables;
import com.bugtracker.database.results.parsed.TicketToLabelParsedQueryResult;

class TicketToLabelsQueryResultParserTest extends ResultParserTestBase {

	@Test
	void testParseSingleRow() {
		super.testParseSingleRow(QueryResultParserUtils.getParserForTable(ETables.TICKET_TO_LABELS),
				new TicketToLabelParsedQueryResult(1, 1));
	}
}