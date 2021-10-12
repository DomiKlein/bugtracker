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
		configuration.setProperty("hibernate.jdbc.time_zone", getSystemTimeZone());

		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().configure()
				.applySettings(configuration.getProperties());

		return configuration.buildSessionFactory(builder.build());
	}

	/** Returns the systems time zone. */
	private static String getSystemTimeZone() {
		ZoneId currentTimezone = ZonedDateTime.now(ZoneId.systemDefault()).getZone();
		return TimeZone.getTimeZone(currentTimezone).getID();
	}
}
