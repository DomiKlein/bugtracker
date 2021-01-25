package com.bugtracker.database.parser;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.bugtracker.database.results.TicketQueryResult;

public class TicketsQueryResultParser extends QueryResultParser<TicketQueryResult> {

	@Override
	public TicketQueryResult parseSingleRow(ResultSet resultSet) throws SQLException {
		return new TicketQueryResult( //
				resultSet.getInt(1), //
				resultSet.getInt(2), //
				resultSet.getInt(3), //
				resultSet.getInt(4), //
				resultSet.getString(5), //
				resultSet.getTimestamp(6) //
		);
	}
}
