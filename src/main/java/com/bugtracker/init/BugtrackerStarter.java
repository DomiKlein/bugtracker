package com.bugtracker.init;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

import com.bugtracker.database.DatabaseConnection;

// TODO : Add comment
public class BugtrackerStarter {

	/** The used logger. */
	private static final Logger LOGGER = LogManager.getLogger(BugtrackerStarter.class);

	/** The name of the package which contains all service classes. */
	private static final String SERVICES_PACKAGE_NAME = "com.bugtracker.services";

	public static void main(String[] args) {
		// Configure database connection
		DatabaseConnection databaseConnection = new DatabaseConnection();
		// TODO : Uncomment
		// databaseConnection.run();

		startServer();
	}

	// TODO : Add comment
	private static void startServer() {
		ServletContextHandler servletContext = prepareServletContext();
		WebAppContext webAppContext;

		HandlerCollection handlerCollection = new HandlerCollection();
		handlerCollection.setHandlers(new Handler[]{servletContext});

		Server jettyServer = new Server(8080);
		jettyServer.setHandler(handlerCollection);
		try {
			jettyServer.start();
			jettyServer.join();
		} catch (Exception e) {
			LOGGER.error("Error while trying to run the webserver");
			jettyServer.destroy();
		}
	}

	// TODO : Add comment
	private static ServletContextHandler prepareServletContext() {
		ServletContextHandler servletContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
		servletContext.setContextPath("/api");

		ServletHolder jerseyServlet = servletContext.addServlet(org.glassfish.jersey.servlet.ServletContainer.class,
				"/*");
		jerseyServlet.setInitOrder(0);
		jerseyServlet.setInitParameter("jersey.config.server.provider.packages", SERVICES_PACKAGE_NAME);

		return servletContext;
	}

	// TODO : Add comment
	private static WebAppContext prepareWebAppContext() {
		// TODO : Implement and use in startWebserver()
		return null;
	}
}
