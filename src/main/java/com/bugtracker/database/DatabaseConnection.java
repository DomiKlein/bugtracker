package com.bugtracker.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.bugtracker.database.enums.ETables;
import com.bugtracker.database.parser.QueryResultParserUtils;
import com.bugtracker.database.results.parsed.TicketParsedQueryResult;
import com.bugtracker.mysql.results.MySQLQueryResult;

public class DatabaseConnection implements Runnable {

	/** The URL of the database. */
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/bugtracker";

	/** The username used for the login. */
	private static final String DATABASE_USER = "root";

	/** The password of the {@link #DATABASE_USER} used for the login. */
	private static final String DATABASE_USER_PASSWORD = "domiklein";

	/** The used logger. */
	private static final Logger LOGGER = LogManager.getLogger(DatabaseConnection.class);

	/** Interval to check if database connection is still alive. */
	private static final int HEALTH_CHECK_INTERVAL_MINUTES = 1;

	/** Singleton database connection. */
	public static Connection databaseConnection;

	@Override
	public void run() {
		establishConnection();
		while (true) {
			try {
				if (databaseConnection.isClosed()) {
					LOGGER.info("Database connection was closed");
					break;
				}
				TimeUnit.MINUTES.sleep(0);

				// TODO : Remove this test code & uncomment stuff in SQL setup
				databaseConnection.createStatement().execute(
						"INSERT INTO Tickets (creatorId, assigneeId, statusId, title, description) VALUES ('0', '0', '0', 'Some Title', 'Some Description')");
				String sqlQuery = SQLQueryBuilder.createEmptyQuery().selectAll().from(ETables.TICKETS).build();
				Set<TicketParsedQueryResult> results = QueryResultParserUtils.getParserForTable(ETables.TICKETS)
						.parseResult(new MySQLQueryResult(databaseConnection.createStatement().executeQuery(sqlQuery)));

				continue;
			} catch (SQLException e) {
				LOGGER.error("A problem occurred with the database connection", e);
				closeConnection();
				break;
			} catch (InterruptedException e) {
				LOGGER.error("Database connection health check was interrupted", e);
				closeConnection();
				break;
			}
		}
	}

	/**
	 * Establishes a database connection.
	 */
	private static void establishConnection() {
		if (DatabaseConnection.databaseConnection == null) {
			try {
				Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER,
						DATABASE_USER_PASSWORD);
				if (connection != null) {
					DatabaseConnection.databaseConnection = connection;
					LOGGER.info("Successfully connected to the MySQL database 'bugtracker'");
				} else {
					throw new SQLException("Unable to connect to database");
				}
			} catch (SQLException e) {
				LOGGER.error("An error occurred while connection to the MySQL database", e);
			}
		}
	}

	/**
	 * Tries to close the {@link #databaseConnection}.
	 */
	private static void closeConnection() {
		try {
			databaseConnection.close();
		} catch (SQLException e) {
			LOGGER.error("Unable to close database connection properly. Connection is probably already closed", e);
		}
	}
}
