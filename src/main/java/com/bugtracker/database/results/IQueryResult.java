package com.bugtracker.database.results;

import java.awt.*;
import java.util.Date;

public interface IQueryResult {

	boolean next();

	Integer getInt(int columnIndex);

	String getString(int columnIndex);

	Date getTimestamp(int columnIndex);

	Color getColor(int columnIndex);
}
