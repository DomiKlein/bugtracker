package com.bugtracker.database.parser;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.NotFoundException;

import com.bugtracker.database.enums.ETables;

/**
 * Utils for {@link QueryResultParser}. Mainly used to retrieve the
 * corresponding parser for an {@link ETables}.
 */
public class QueryResultParserUtils {
	private static final Map<ETables, QueryResultParser> PARSER_BY_TABLE_NAME;

	// TODO : Implement remaining parsers
	static {
		PARSER_BY_TABLE_NAME = new HashMap<>();
		PARSER_BY_TABLE_NAME.put(ETables.USERS, null);
		PARSER_BY_TABLE_NAME.put(ETables.STATUS, null);
		PARSER_BY_TABLE_NAME.put(ETables.LABELS, null);
		PARSER_BY_TABLE_NAME.put(ETables.TICKETS, new TicketsQueryResultParser());
		PARSER_BY_TABLE_NAME.put(ETables.TICKET_COMMENTS, null);
		PARSER_BY_TABLE_NAME.put(ETables.WORKFLOW, null);
		PARSER_BY_TABLE_NAME.put(ETables.TICKET_TO_LABELS, null);
	}

	/**
	 * Returns the parser for the given table.
	 */
	public static QueryResultParser getParserForTable(ETables table) {
		QueryResultParser parser = PARSER_BY_TABLE_NAME.get(table);
		if (parser == null) {
			throw new NotFoundException("No parser available for table " + table.name);
		}
		return parser;
	}
}
