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

	static {
		PARSER_BY_TABLE_NAME = new HashMap<>();
		PARSER_BY_TABLE_NAME.put(ETables.USERS, new UsersQueryResultParser());
		PARSER_BY_TABLE_NAME.put(ETables.STATUS, new StatusQueryResultParser());
		PARSER_BY_TABLE_NAME.put(ETables.LABELS, new LabelsQueryResultParser());
		PARSER_BY_TABLE_NAME.put(ETables.TICKETS, new TicketsQueryResultParser());
		PARSER_BY_TABLE_NAME.put(ETables.TICKET_COMMENTS, new TicketCommentsQueryResultParser());
		PARSER_BY_TABLE_NAME.put(ETables.WORKFLOW, new WorkflowQueryResultParser());
		PARSER_BY_TABLE_NAME.put(ETables.TICKET_TO_LABELS, new TicketToLabelsQueryResultParser());
	}

	/**
	 * Returns the parser for the given table.
	 */
	public static QueryResultParser getParserForTable(ETables table) {
		QueryResultParser parser = PARSER_BY_TABLE_NAME.getOrDefault(table, null);

		if (parser == null) {
			throw new NotFoundException("No parser available for table " + table.name);
		}

		return parser;
	}
}
