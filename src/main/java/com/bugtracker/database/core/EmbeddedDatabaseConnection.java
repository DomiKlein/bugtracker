package com.bugtracker.database.core;

import org.apache.log4j.Logger;

import com.bugtracker.utils.StringUtils;
import com.bugtracker.utils.TimeUtils;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DB;
import ch.vorburger.mariadb4j.DBConfiguration;
import ch.vorburger.mariadb4j.DBConfigurationBuilder;

/** An embedded database connection. */
public class EmbeddedDatabaseConnection extends MySQLDatabaseConnection {

	/** The embedded database. */
	private DB database;

	/** Command to change the password of the default user. */
	private static final String CHANGE_PASSWORD_COMMAND = String.format(
			"ALTER USER '%s'@'localhost' IDENTIFIED BY '%s';\nflush privileges;", STANDARD_USERNAME, STANDARD_PASSWORD);

	/** The used logger. */
	private static final Logger LOGGER = Logger.getLogger(EmbeddedDatabaseConnection.class);

	@Override
	protected void setup() {
		try {
			database = DB.newEmbeddedDB(getConfiguration());
			database.start();
			database.run(CHANGE_PASSWORD_COMMAND, STANDARD_USERNAME, StringUtils.EMPTY_STRING);
			database.createDB(STANDARD_TABLE_NAME, STANDARD_USERNAME, STANDARD_PASSWORD);
		} catch (ManagedProcessException e) {
			LOGGER.fatal("Failed to initialize embedded database", e);
			terminate();
		}
		super.setup();
	}

	/** Returns the configuration for the database. */
	private static DBConfiguration getConfiguration() {
		DBConfigurationBuilder configBuilder = DBConfigurationBuilder.newBuilder();
		configBuilder.addArg("--default-time-zone=" + TimeUtils.getSystemTimeZoneOffsetFromUTC());
		configBuilder.setPort(STANDARD_PORT);
		configBuilder.setSecurityDisabled(false);

		return configBuilder.build();
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();
		try {
			database.stop();
		} catch (ManagedProcessException e) {
			LOGGER.warn("Unable to stop embedded database (probably already stopped)");
		}
	}

	@Override
	public EDatabaseType getType() {
		return EDatabaseType.EMBEDDED;
	}
}
