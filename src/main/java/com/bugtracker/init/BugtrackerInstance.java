package com.bugtracker.init;

import com.bugtracker.database.core.MySQLDatabaseConnection;
import com.bugtracker.database.core.WebserverConnection;

/**
 * Represents a Bugtracker instance.
 */
public class BugtrackerInstance {

	/** The singleton instance. */
	private static BugtrackerInstance INSTANCE = null;

	/** The thread which establishes the database connections. */
	private final MySQLDatabaseConnection databaseConnectionThread;

	/** The thread which runs the webserver. */
	private final WebserverConnection webserverConnection;

	protected BugtrackerInstance(MySQLDatabaseConnection databaseConnectionThread,
			WebserverConnection webserverConnection) {
		this.databaseConnectionThread = databaseConnectionThread;
		this.webserverConnection = webserverConnection;
	}

	/**
	 * Sets the value of the singleton instance.
	 */
	protected static void setInstance(BugtrackerInstance instance) {
		BugtrackerInstance.INSTANCE = instance;
	}

	/**
	 * Returns the current bugtracker instance.
	 */
	public static BugtrackerInstance getInstance() {
		return INSTANCE;
	}

	/** Terminates the connection. */
	protected static void terminate() {
		INSTANCE.databaseConnectionThread.terminate();
		INSTANCE.webserverConnection.terminate();
		INSTANCE = null;
	}

	/**
	 * @see #databaseConnectionThread
	 */
	public MySQLDatabaseConnection getDatabaseConnectionThread() {
		return databaseConnectionThread;
	}

	/**
	 * @see #webserverConnection
	 */
	public WebserverConnection getWebserverConnection() {
		return webserverConnection;
	}
}
