package com.bugtracker.database.parser;

import org.junit.jupiter.api.Test;

import com.bugtracker.database.results.parsed.LabelParsedQueryResult;

/** Tests for the {@link LabelsQueryResultParser} */
class LabelsQueryResultParserTest extends ResultParserTestBase {

	@Test
	void testParseSingleRow() {
		super.testParseSingleRow(new LabelsQueryResultParser(), new LabelParsedQueryResult(1, "Test 1"));
	}
}