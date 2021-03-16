package com.bugtracker.database.parser;

import java.util.Date;

import org.junit.jupiter.api.Test;

import com.bugtracker.database.results.parsed.TicketCommentParsedQueryResult;

/** Tests for the {@link TicketCommentsQueryResultParser} */
class TicketCommentsQueryResultParserTest extends ResultParserTestBase {

	@Test
	void testParseSingleRow() {
		super.testParseSingleRow(new TicketCommentsQueryResultParser(),
				new TicketCommentParsedQueryResult(1, 1, "Test 1", new Date(1)));
	}
}