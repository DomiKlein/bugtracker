package com.bugtracker.database.core;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.bugtracker.utils.TimeUtils;

/** Class to initialize a database connection. */
public class DatabaseInitializer {

	private static final String DIALECT = "org.hibernate.dialect.MySQL5Dialect";

	/**
	 * Initializes a connection to the database, which is reachable at the given
	 * {@code databaseUrl} and returns the according {@link SessionFactory}.
	 */
	public static SessionFactory initDatabase(String databaseUrl) {
		Configuration configuration = new Configuration();
		configuration.setProperty("hibernate.connection.url", databaseUrl);
		configuration.setProperty("hibernate.jdbc.time_zone", TimeUtils.getSystemTimeZone());
		configuration.setProperty("hibernate.dialect", DIALECT);

		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().configure()
				.applySettings(configuration.getProperties());

		return configuration.buildSessionFactory(builder.build());
	}
}
