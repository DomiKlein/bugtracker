package com.bugtracker.database.parser;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import com.bugtracker.database.results.parsed.IParsedQueryResult;

/** Test base for tests which test sublasses of {@link QueryResultParser} */
public abstract class ResultParserTestBase {

	void testParseSingleRow(QueryResultParser parserUnderTest, IParsedQueryResult expectedResult) {
		TestQueryResult testResult = new TestQueryResult(1);

		Set<IParsedQueryResult> actualResult = parserUnderTest.parseResult(testResult);

		assertThat(actualResult).isEqualTo(Set.of(expectedResult));
	}
}
