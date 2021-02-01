package com.bugtracker.database.parser;

import org.junit.jupiter.api.Test;

import com.bugtracker.database.enums.ETables;
import com.bugtracker.database.results.parsed.WorkflowParsedQueryResult;

class WorkflowQueryResultParserTest extends ResultParserTestBase {

	@Test
	void testParseSingleRow() {
		super.testParseSingleRow(QueryResultParserUtils.getParserForTable(ETables.WORKFLOW),
				new WorkflowParsedQueryResult(1, 1));
	}
}