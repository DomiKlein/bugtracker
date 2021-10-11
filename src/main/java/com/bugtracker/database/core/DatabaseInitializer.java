package com.bugtracker.database.core;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.TimeZone;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/** Class to initialize a database connection. */
public class DatabaseInitializer {

	/**
	 * Initializes a connection to the database, which is reachable at the given
	 * {@code databaseUrl} and returns the according {@link SessionFactory}.
	 */
	public static SessionFactory initDatabase(String databaseUrl) {
		Configuration configuration = new Configuration();
		configuration.setProperty("hibernate.connection.url", databaseUrl);

		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().configure()
				.applySettings(configuration.getProperties());

		SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
		// TODO : Check if this setup is working as expected
		ZoneId currentTimezone = ZonedDateTime.now(ZoneId.systemDefault()).getZone();
		sessionFactory.withOptions().jdbcTimeZone(TimeZone.getTimeZone(currentTimezone));

		return sessionFactory;
	}
}
