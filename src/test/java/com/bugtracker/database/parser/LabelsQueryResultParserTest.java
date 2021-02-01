package com.bugtracker.database.parser;

import org.junit.jupiter.api.Test;

import com.bugtracker.database.enums.ETables;
import com.bugtracker.database.results.parsed.LabelParsedQueryResult;

class LabelsQueryResultParserTest extends ResultParserTestBase {

	@Test
	void testParseSingleRow() {
		super.testParseSingleRow(QueryResultParserUtils.getParserForTable(ETables.LABELS),
				new LabelParsedQueryResult(1, "Test 1"));
	}
}