package com.bugtracker.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

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

	/** The number of retires until the connection establishment actually fails */
	private static final int MAX_NUMBER_OF_RETRIES = 3;

	/** The seconds to wait for a retry if the connection cannot be established */
	private static final int SECONDS_UNTIL_RETRY = 30;

	/** The number of actual retries which failed */
	private int failedConnectionAttempts = 0;

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
				TimeUnit.MINUTES.sleep(HEALTH_CHECK_INTERVAL_MINUTES);
			} catch (SQLException e) {
				LOGGER.error("A problem occurred with the database connection", e);
				break;
			} catch (InterruptedException e) {
				LOGGER.warn("Database connection health check was interrupted", e);
				break;
			}
		}
		closeConnection();
	}

	/**
	 * Establishes a database connection.
	 */
	private void establishConnection() {
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
				retry(e);
			}
		}
	}

	/**
	 * Waits for the {@linkplain #SECONDS_UNTIL_RETRY specified time interval} and
	 * afterwards again tries to establish the connection to the database, but only
	 * if we have not exceeded the {@link #MAX_NUMBER_OF_RETRIES}.
	 * <p>
	 * Will increase the {@linkplain #failedConnectionAttempts retries counter}.
	 */
	private void retry(SQLException exception) {
		if (failedConnectionAttempts < MAX_NUMBER_OF_RETRIES) {
			LOGGER.warn("Attempt to connect to the database failed. Will retry "
					+ (MAX_NUMBER_OF_RETRIES - failedConnectionAttempts) + " more times");
			failedConnectionAttempts++;
			try {
				TimeUnit.SECONDS.sleep(SECONDS_UNTIL_RETRY);
			} catch (InterruptedException e) {
				LOGGER.warn("Timer which waits for the next retry was interrupted", e);
			}
			establishConnection();
			return;
		}
		LOGGER.error("An error occurred while connection to the MySQL database", exception);
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
