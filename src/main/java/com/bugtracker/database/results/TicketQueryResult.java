package com.bugtracker.database.results;

import com.bugtracker.database.enums.Tables;

public class TicketQueryResult implements QueryResult {
	// TODO : Implement

	@Override
	public Tables getTable() {
		return Tables.TICKETS;
	}
}
