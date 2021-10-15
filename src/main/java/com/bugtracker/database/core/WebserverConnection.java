package com.bugtracker.database.core;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;

import com.bugtracker.utils.ExtendedThread;

/** Represents the API server. */
public class WebserverConnection extends ExtendedThread {

	/** The used logger. */
	private static final Logger LOGGER = Logger.getLogger(WebserverConnection.class);

	/** The API server. */
	private final Server webserver;

	public WebserverConnection(Server webserver) {
		this.webserver = webserver;
	}

	@Override
	protected void setup() {
		// Nothing to set up
	}

	@Override
	protected void execute() {
		try {
			webserver.start();
			webserver.join();
		} catch (Exception e) {
			LOGGER.fatal("Webserver connection was closed", e);
			terminate();
		}
	}

	@Override
	protected void cleanUp() {
		try {
			webserver.stop();
			webserver.destroy();
		} catch (Exception e) {
			LOGGER.warn("Unable to stop webserver (probably already stopped)", e);
		}
	}
}
