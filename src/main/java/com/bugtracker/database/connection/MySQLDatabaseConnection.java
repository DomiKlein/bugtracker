package com.bugtracker.database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.bugtracker.database.query.SQLQuery;

/** Represents the database connection. */
public class MySQLDatabaseConnection extends DatabaseConnection {

	/** The URL of the database */
	private final String databaseUrl;

	/** The standard URL of the database used by Docker. */
	private static final String STANDARD_DOCKER_DATABASE_URL = "jdbc:mysql://localhost:3306/bugtracker";

	/** The username used for the login. */
	private static final String DATABASE_USER = "root";

	/** The password of the {@link #DATABASE_USER} used for the login. */
	private static final String DATABASE_USER_PASSWORD = "domiklein";

	/** The used logger. */
	private static final Logger LOGGER = LogManager.getLogger(MySQLDatabaseConnection.class);

	/** Interval to check if database connection is still alive. */
	private static final int HEALTH_CHECK_INTERVAL_MINUTES = 1;

	/** The seconds to wait for a retry if the connection cannot be established. */
	private static final int SECONDS_UNTIL_RETRY = 5;

	/** Singleton database connection. */
	public static Connection databaseConnection;

	/** Use database at standard docker location. */
	public MySQLDatabaseConnection() {
		this(STANDARD_DOCKER_DATABASE_URL);
	}

	/** Use different */
	public MySQLDatabaseConnection(String databaseUrl) {
		this.databaseUrl = databaseUrl;
	}

	@Override
	protected void preRun() {
		establishConnection();
	}

	@Override
	protected boolean doRun() {
		try {
			if (!databaseConnection.isValid(30)) {
				LOGGER.info("Database connection was closed");
				return false;
			}
			TimeUnit.MINUTES.sleep(HEALTH_CHECK_INTERVAL_MINUTES);
		} catch (SQLException e) {
			LOGGER.error("A problem occurred with the database connection", e);
			return false;
		} catch (InterruptedException e) {
			LOGGER.warn("Database connection health check was interrupted", e);
			return false;
		}
		return true;
	}

	/**
	 * Establishes a database connection.
	 */
	private void establishConnection() {
		while (MySQLDatabaseConnection.databaseConnection == null) {
			try {
				Connection connection = DriverManager.getConnection(databaseUrl, DATABASE_USER, DATABASE_USER_PASSWORD);
				if (connection != null) {
					MySQLDatabaseConnection.databaseConnection = connection;
					LOGGER.info("Successfully connected to the MySQL database 'bugtracker'");
				}
			} catch (SQLException e) {
				// Docker container is probably not running (retry)
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

	@Override
	protected void afterRun() {
		closeConnection();
	}

	@Override
	public void query(SQLQuery query) {
		// TODO
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
