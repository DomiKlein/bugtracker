package com.bugtracker.database.core;

import org.apache.log4j.Logger;

/** Class also log uncaught exceptions. */
public class UncaughtExceptionLogger implements Thread.UncaughtExceptionHandler {

	private static final Logger LOGGER = Logger.getLogger(UncaughtExceptionLogger.class);

	@Override
	public void uncaughtException(Thread t, Throwable ex) {
		LOGGER.error("Uncaught exception in thread: " + t.getName(), ex);
	}

}
