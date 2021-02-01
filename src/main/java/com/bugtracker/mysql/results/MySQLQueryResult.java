package com.bugtracker.mysql.results;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.bugtracker.database.results.IQueryResult;

/**
 * Represents the results of a query on a imaginary database.
 * <p>
 * Wrapper class for a {@link ResultSet}.
 */
public class MySQLQueryResult implements IQueryResult {

	private final ResultSet resultSet;

	private final Logger LOGGER = LogManager.getLogger(MySQLQueryResult.class);

	public MySQLQueryResult(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

	@Override
	public boolean next() {
		try {
			return this.resultSet.next();
		} catch (SQLException e) {
			LOGGER.error("An error occurred while calling 'next()' on ResultSet", e);
			return false;
		}
	}

	@Override
	public Integer getInt(int columnIndex) {
		try {
			return resultSet.getInt(columnIndex);
		} catch (SQLException e) {
			LOGGER.error("An error occurred while calling 'getInt()' on ResultSet", e);
			return null;
		}
	}

	@Override
	public String getString(int columnIndex) {
		try {
			return resultSet.getString(columnIndex);
		} catch (SQLException e) {
			LOGGER.error("An error occurred while calling 'getString()' on ResultSet", e);
			return null;
		}
	}

	@Override
	public Date getTimestamp(int columnIndex) {
		try {
			return new Date(resultSet.getTimestamp(columnIndex).getTime());
		} catch (SQLException e) {
			LOGGER.error("An error occurred while calling 'getTimestamp()' on ResultSet", e);
			return null;
		}
	}

	@Override
	public Color getColor(int columnIndex) {
		try {
			return new Color(resultSet.getInt(columnIndex));
		} catch (SQLException e) {
			LOGGER.error("An error occurred while calling 'getInt()' on ResultSet", e);
			return null;
		}
	}
}
