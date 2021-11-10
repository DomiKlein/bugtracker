package com.bugtracker.init;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ErrorPageErrorHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletContainer;

import com.bugtracker.database.core.EmbeddedDatabaseConnection;
import com.bugtracker.database.core.MySQLDatabaseConnection;
import com.bugtracker.database.core.UncaughtExceptionLogger;
import com.bugtracker.database.core.WebserverConnection;

/**
 * The main class used to establish the database connection and start all
 * necessary servers, the webserver and REST server respectively.
 */
public class BugtrackerStarter {

	/** The name of the package which contains all service classes. */
	private static final String SERVICES_PACKAGE_NAME = "com.bugtracker.services";

	private static final Logger LOGGER = Logger.getLogger(BugtrackerStarter.class);

	public static void main(String[] args) {
		LOGGER.info("Starting...");
		setupFailureActions();
		setupServers();
	}

	/**
	 * Executes some essential setup steps before the program should actually do
	 * something.
	 */
	private static void setupFailureActions() {
		// Add hook to properly close all connections on termination
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			LOGGER.info("Stopping...");
			BugtrackerInstance.terminate();
		}));

		// Handle uncaught exception properly
		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionLogger());
	}

	/** Starts and establishes all connections to necessary services. */
	private static void setupServers() {
		// Establish connection to database
		MySQLDatabaseConnection databaseConnection = prepareDatabaseConnection();
		LOGGER.info("Database type: " + databaseConnection.getType());
		databaseConnection.start();

		// Establish connection to webserver
		Server webserver = prepareWebserver();
		WebserverConnection webserverConnection = new WebserverConnection(webserver);
		webserverConnection.start();

		BugtrackerInstance.setInstance(new BugtrackerInstance(databaseConnection, webserverConnection));
	}

	/** Prepares the connection to the database by reading system properties. */
	private static MySQLDatabaseConnection prepareDatabaseConnection() {
		String externalProperty = System.getProperty("com.bugtracker.database.external");
		if (externalProperty == null) {
			return new EmbeddedDatabaseConnection();
		}

		boolean isExternal = Boolean.parseBoolean(externalProperty);
		if (!isExternal) {
			return new EmbeddedDatabaseConnection();
		}

		String externalDatabaseURL = System.getProperty("com.bugtracker.database.url");
		if (externalDatabaseURL == null) {
			return new MySQLDatabaseConnection();
		}
		return new MySQLDatabaseConnection(externalDatabaseURL);
	}

	/**
	 * Prepares the webserver.
	 */
	private static Server prepareWebserver() {
		Server webserver = new Server(8080);

		ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
		webserver.setHandler(contextHandler);
		contextHandler.setContextPath("/");

		// Let React Router handle wrong pages
		ErrorPageErrorHandler errorHandler = new ErrorPageErrorHandler();
		errorHandler.addErrorPage(404, "/index.html");
		contextHandler.setErrorHandler(errorHandler);

		// Use a Jersey servlet to create the API
		ServletHolder jerseyServlet = contextHandler.addServlet(ServletContainer.class, "/api/*");
		jerseyServlet.setInitOrder(1);
		jerseyServlet.setInitParameter(ServerProperties.PROVIDER_PACKAGES, SERVICES_PACKAGE_NAME);

		// Use a DefaultServlet to serve static files.
		ServletHolder staticServlet = contextHandler.addServlet(DefaultServlet.class, "/*");
		staticServlet.setInitParameter("resourceBase", "src/ui/dist");

		return webserver;
	}
}
