package com.bugtracker.database.parser;

import java.awt.*;

import org.junit.jupiter.api.Test;

import com.bugtracker.database.enums.ETables;
import com.bugtracker.database.results.parsed.StatusParsedQueryResult;

class StatusQueryResultParserTest extends ResultParserTestBase {

	@Test
	void testParseSingleRow() {
		super.testParseSingleRow(QueryResultParserUtils.getParserForTable(ETables.STATUS),
				new StatusParsedQueryResult(1, "Test 1", new Color(1)));
	}
}