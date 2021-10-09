package com.bugtracker.init;

import com.bugtracker.database.core.MySQLDatabaseConnection;

/**
 * Represents a Bugtracker instance.
 */
public class BugtrackerInstance {

	/** The singleton instance. */
	private static BugtrackerInstance INSTANCE = null;

	/** The thread which establishes the database connections. */
	private final MySQLDatabaseConnection databaseConnectionThread;

	/** The thread which runs the Jetty server. */
	private final Thread jettyServerThread;

	protected BugtrackerInstance(MySQLDatabaseConnection databaseConnectionThread, Thread jettyServerThread) {
		this.databaseConnectionThread = databaseConnectionThread;
		this.jettyServerThread = jettyServerThread;
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
	protected static BugtrackerInstance getInstance() {
		return INSTANCE;
	}

	/**
	 * @see #databaseConnectionThread
	 */
	public Thread getDatabaseConnectionThread() {
		return databaseConnectionThread;
	}

	/**
	 * @see #jettyServerThread
	 */
	public Thread getJettyServerThread() {
		return jettyServerThread;
	}
}
