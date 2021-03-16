package com.bugtracker.init;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/** Represents the database connection. */
public class DatabaseConnection extends Thread {

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

	/** The seconds to wait for a retry if the connection cannot be established. */
	private static final int SECONDS_UNTIL_RETRY = 5;

	/** Indicates whether the connection with the database is valid. */
	private boolean isConnected = false;

	/** Singleton database connection. */
	public static Connection databaseConnection;

	@Override
	public void run() {
		establishConnection();
		while (true) {
			try {
				if (databaseConnection.isValid(30)) {
					LOGGER.info("Database connection was closed");
					isConnected = false;
					break;
				}
				TimeUnit.MINUTES.sleep(HEALTH_CHECK_INTERVAL_MINUTES);
			} catch (SQLException e) {
				LOGGER.error("A problem occurred with the database connection", e);
				break;
			} catch (InterruptedException e) {
				LOGGER.warn("Database connection health check was interrupted", e);
			}
		}
		closeConnection();
	}

	/**
	 * Establishes a database connection.
	 */
	private void establishConnection() {
		while (DatabaseConnection.databaseConnection == null) {
			try {
				Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER,
						DATABASE_USER_PASSWORD);
				if (connection != null) {
					DatabaseConnection.databaseConnection = connection;
					LOGGER.info("Successfully connected to the MySQL database 'bugtracker'");
					isConnected = true;
				}
			} catch (SQLException e) {
				// Docker container is probably not running
			} finally {
				waitForNextTry();
			}
		}
	}

	/**
	 * Suspends the current thread for {@link #SECONDS_UNTIL_RETRY}.
	 */
	private static void waitForNextTry() {
		try {
			TimeUnit.SECONDS.sleep(SECONDS_UNTIL_RETRY);
		} catch (InterruptedException e) {
			LOGGER.error("Timer which silently waits for the next retry was interrupted", e);
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
