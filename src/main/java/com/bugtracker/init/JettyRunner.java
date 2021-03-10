package com.bugtracker.init;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;

public class JettyRunner implements Runnable {

	/** The used logger. */
	private static final Logger LOGGER = LogManager.getLogger(JettyRunner.class);

	private Server jettyServer;

	protected JettyRunner(Server jettyServer) {
		this.jettyServer = jettyServer;
	}
	@Override
	public void run() {
		try {
			jettyServer.start();
			jettyServer.join();
		} catch (Exception e) {
			LOGGER.error("Error while trying to run the webserver");
			jettyServer.destroy();
		}
	}
}
