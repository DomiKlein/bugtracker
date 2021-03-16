package com.bugtracker.database.parser;

import org.junit.jupiter.api.Test;

import com.bugtracker.database.results.parsed.WorkflowParsedQueryResult;

/** Tests for the {@link WorkflowQueryResultParser} */
class WorkflowQueryResultParserTest extends ResultParserTestBase {

	@Test
	void testParseSingleRow() {
		super.testParseSingleRow(new WorkflowQueryResultParser(), new WorkflowParsedQueryResult(1, 1));
	}
}