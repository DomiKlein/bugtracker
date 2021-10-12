package com.bugtracker.database.core;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.bugtracker.database.model.DatabaseEntity;

/** Represents the database connection. */
public class MySQLDatabaseConnection extends Thread {

	/** The URL of the database */
	private final String databaseUrl;

	/** The standard URL of the database used by Docker. */
	private static final String STANDARD_DOCKER_DATABASE_URL = "jdbc:mysql://localhost:3306/bugtracker";

	/** The used logger. */
	private static final Logger LOGGER = LogManager.getLogger(MySQLDatabaseConnection.class);

	/** Interval to check if database connection is still alive. */
	private static final int HEALTH_CHECK_INTERVAL_MINUTES = 1;

	/** Value to check whether the thread is still running. */
	private volatile boolean running = true;

	/** Session factory to send queries to the database */
	protected EntityManagerFactory entityManagerFactory;

	/** Use database at standard docker location. */
	public MySQLDatabaseConnection() {
		this(STANDARD_DOCKER_DATABASE_URL);
	}

	/** Use different */
	public MySQLDatabaseConnection(String databaseUrl) {
		super();
		this.databaseUrl = databaseUrl;
	}

	@Override
	public void run() {
		entityManagerFactory = DatabaseInitializer.initDatabase(databaseUrl);

		while (running) {
			try {
				if (!entityManagerFactory.isOpen()) {
					LOGGER.info("Database connection was closed");
					break;
				}
				TimeUnit.MINUTES.sleep(HEALTH_CHECK_INTERVAL_MINUTES);
			} catch (InterruptedException e) {
				LOGGER.warn("Database connection health check was interrupted", e);
				break;
			}
		}
		terminate();
	}

	/** Terminates the thread and closes the connection to the database */
	public void terminate() {
		running = false;
		entityManagerFactory.close();
	}

	/** Save the given object in the database. */
	public <T extends DatabaseEntity> void saveData(T object) {
		executeQuery((em -> {
			if (object.isDetached() || object.forceMerge()) {
				em.merge(object);
			} else {
				em.persist(object);
			}
			return object;
		}));
	}

	/** Finds the object with the given id. */
	public <T extends DatabaseEntity> T readData(Class<T> clazz, Serializable id) {
		return executeQuery((em -> em.find(clazz, id)));
	}

	/** Updates the given object in the database. */
	public <T extends DatabaseEntity> void updateData(T object) {
		executeQuery((em) -> em.merge(object));
	}

	/** Executes the given procedure on the database. */
	private <T> T executeQuery(Function<EntityManager, T> query) {
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = null;

		try {
			transaction = em.getTransaction();
			transaction.begin();
			T result = query.apply(em);
			transaction.commit();
			return result;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			LOGGER.error("Failed to execute query", e);
		} finally {
			em.close();
		}

		return null;
	}
}
