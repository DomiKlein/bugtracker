package com.bugtracker.init;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * The main class used to establish the database connection and start all
 * necessary servers, the webserver and REST server respectively.
 */
public class BugtrackerStarter {

	/** The name of the package which contains all service classes. */
	private static final String SERVICES_PACKAGE_NAME = "com.bugtracker.services";

	public static void main(String[] args) {
		// Establish database connection
		DatabaseConnection databaseConnection = new DatabaseConnection();
		databaseConnection.start();

		// Start jetty server
		Server jettyServer = prepareJettyServer();
		JettyRunner jettyRunner = new JettyRunner(jettyServer);
		jettyRunner.start();

		BugtrackerInstance.setInstance(new BugtrackerInstance(databaseConnection, jettyRunner));
	}

	/**
	 * Starts the webserver and REST server.
	 */
	private static Server prepareJettyServer() {
		ServletContextHandler servletContext = prepareServletContext();
		WebAppContext webAppContext = prepareWebAppContext();

		HandlerCollection handlerCollection = new HandlerCollection();
		handlerCollection.setHandlers(new Handler[]{servletContext, webAppContext});

		Server jettyServer = new Server(8080);
		jettyServer.setHandler(handlerCollection);

		return jettyServer;
	}

	/**
	 * Creates a {@link ServletContextHandler} which is used for the REST server.
	 */
	private static ServletContextHandler prepareServletContext() {
		ServletContextHandler servletContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
		servletContext.setContextPath("/api");

		ServletHolder jerseyServlet = servletContext.addServlet(org.glassfish.jersey.servlet.ServletContainer.class,
				"/*");
		jerseyServlet.setInitOrder(0);
		jerseyServlet.setInitParameter("jersey.config.server.provider.packages", SERVICES_PACKAGE_NAME);

		return servletContext;
	}

	/**
	 * Creates a {@link WebAppContext} which is used for the webserver.
	 */
	private static WebAppContext prepareWebAppContext() {
		WebAppContext webapp = new WebAppContext();
		webapp.setResourceBase("src/main/webapp");
		webapp.setContextPath("/");

		return webapp;
	}
}
