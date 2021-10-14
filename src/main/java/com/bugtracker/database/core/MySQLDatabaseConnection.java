package com.bugtracker.database.core;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;

import com.bugtracker.database.model.DatabaseEntity;
import com.bugtracker.utils.ExtendedThread;

/** Represents the database connection. */
public class MySQLDatabaseConnection extends ExtendedThread {

	/** The URL of the database */
	private final String databaseUrl;

	/** The standard URL of the database used by Docker. */
	private static final String STANDARD_DOCKER_DATABASE_URL = "jdbc:mysql://localhost:3306/bugtracker";

	/** The used logger. */
	private static final Logger LOGGER = Logger.getLogger(MySQLDatabaseConnection.class);

	/** Interval to check if database connection is still alive. */
	private static final int HEALTH_CHECK_INTERVAL_MINUTES = 1;

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
	protected void setup() {
		try {
			entityManagerFactory = DatabaseInitializer.initDatabase(databaseUrl);
		} catch (Exception e) {
			LOGGER.fatal("Could not establish connection to database", e);
			terminate();
		}
	}

	@Override
	protected void execute() {
		try {
			if (!this.isConnected()) {
				LOGGER.fatal("Databased connection was closed");
				terminate();
			}
			TimeUnit.MINUTES.sleep(HEALTH_CHECK_INTERVAL_MINUTES);
		} catch (InterruptedException e) {
			LOGGER.warn("Database connection health check was interrupted", e);
		}
	}

	private boolean isConnected() {
		return Boolean.TRUE.equals(this.executeQuery(entityManager -> {
			try {
				Object res = entityManager.createNativeQuery("select 1").getSingleResult();
				if (res != null) {
					return Boolean.TRUE;
				}
			} catch (Exception e) {
				// Connection lost
			}
			return Boolean.FALSE;
		}, false));
	}

	/** Terminates the thread and closes the connection to the database */
	@Override
	protected void cleanUp() {
		if (entityManagerFactory != null) {
			entityManagerFactory.close();
		}
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
		return executeQuery(query, true);
	}

	/** Executes the given procedure on the database. */
	private <T> T executeQuery(Function<EntityManager, T> query, boolean createLog) {
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
			if (createLog) {
				LOGGER.error("Failed to execute query", e);
			}
		} finally {
			em.close();
		}

		return null;
	}
}
