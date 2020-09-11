package com.bugtracker.database.parser;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.NotFoundException;

import com.bugtracker.database.enums.Tables;

/**
 * Utils for {@link QueryResultParser}. Mainly used to retrieve the
 * corresponding parser for an {@link Tables}.
 */
public class QueryResultParserUtils {
	private static final Map<Tables, QueryResultParser> parserByTableName;
	static {
		parserByTableName = new HashMap<>();
		parserByTableName.put(Tables.USERS, null);
		parserByTableName.put(Tables.STATUS, null);
		parserByTableName.put(Tables.LABELS, null);
		parserByTableName.put(Tables.TICKETS, new TicketsQueryResultParser());
		parserByTableName.put(Tables.TICKET_COMMENTS, null);
		parserByTableName.put(Tables.WORKFLOW, null);
		parserByTableName.put(Tables.TICKET_TO_LABELS, null);
	}

	/**
	 * Returns the parser for the given table.
	 */
	public static QueryResultParser getParserForTable(Tables table) {
		QueryResultParser parser = parserByTableName.get(table);
		if (parser == null) {
			throw new NotFoundException("No parser available for table " + table.name);
		}
		return parser;
	}
}
