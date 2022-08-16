package com.bugtracker.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Class also log uncaught exceptions. */
public class UncaughtExceptionLogger implements Thread.UncaughtExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(UncaughtExceptionLogger.class);

	@Override
	public void uncaughtException(Thread t, Throwable ex) {
		LOGGER.error("Uncaught exception in thread: " + t.getName(), ex);
	}

}
