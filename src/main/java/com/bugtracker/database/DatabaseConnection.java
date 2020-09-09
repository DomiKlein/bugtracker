package com.bugtracker.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class DatabaseConnection implements Runnable {

	/**
	 * The URL of the database
	 */
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/bugtracker";

	/**
	 * The username used for the login.
	 */
	private static final String DATABASE_USER = "root";

	/**
	 * The password of the {@link #DATABASE_USER} used for the login.
	 */
	private static final String DATABASE_USER_PASSWORD = "domiklein";

	/**
	 * The used logger.
	 */
	private static final Logger LOGGER = LogManager.getLogger(DatabaseConnection.class);

	/**
	 * Singleton database connection.
	 */
	public static final Connection databaseConnection = establishConnection();

	/**
	 * Establishes a database connection and returns it.
	 */
	private static Connection establishConnection() {
		Connection databaseConnection = null;
		try {
			databaseConnection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_USER_PASSWORD);
			if (databaseConnection != null) {
				LOGGER.info("Successfully connected to the MySQL database 'bugtracker'");
			}
		} catch (SQLException e) {
			LOGGER.error("An error occurred while connection to the MySQL database", e);
		}
		return databaseConnection;
	}

	@Override
	public void run() {
		while (true) {
			try {
				if (databaseConnection.isClosed()) {
					LOGGER.info("Database connection was closed");
					break;
				}
				TimeUnit.MINUTES.sleep(1);
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
	 * Tries to close the {@link #databaseConnection}.
	 */
	private void closeConnection() {
		try {
			databaseConnection.close();
		} catch (SQLException e) {
			LOGGER.error("Unable to close database connection properly. Connection is probably already closed", e);
		}
	}
}
