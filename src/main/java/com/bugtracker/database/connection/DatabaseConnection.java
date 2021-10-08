package com.bugtracker.database.connection;

import com.bugtracker.database.query.SQLQuery;

public abstract class DatabaseConnection extends Thread {

	private volatile boolean isRunning = false;

	@Override
	public final void run() {
		preRun();

		isRunning = true;
		while (isRunning) {
			isRunning = doRun();
		}
	}

	/** Stops the thread */
	protected final void stopRun() {
		isRunning = false;
		afterRun();
	}

	/** Method to execute before thread should start running */
	protected abstract void preRun();

	/** Method which is constantly executed while the thread is running */
	protected abstract boolean doRun();

	/** Method to execute which is executed if the thread is stopped */
	protected abstract void afterRun();

	/** Execute a SQL query */
	public abstract void query(SQLQuery query);

	/** Returns whether the connection to the database is still valid. */
	public final boolean hasValidConnection() {
		return isRunning;
	}
}
