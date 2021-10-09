package com.bugtracker.database.core;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

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
	protected SessionFactory sessionFactory;

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
		sessionFactory = DatabaseInitializer.initDatabase(databaseUrl);

		while (running) {
			try {
				if (sessionFactory.isClosed()) {
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
		sessionFactory.close();
	}

	/** Save the given object in the database. */
	public void saveData(Object o) {
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			session.save(o);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	/** Finds the object with the given id. */
	public Object readData(Class clazz, Serializable id) {
		return executeQuery((session -> session.get(clazz, id)));
	}

	/** Finds the object where the given property equals the given {@code value}. */
	public Object readDataWithConstraints(Class clazz, String propertyName, Object value) {
		return executeQuery((session -> {
			Criteria criteria = session.createCriteria(clazz);
			return criteria.add(Restrictions.eq(propertyName, value)).uniqueResult();
		}));
	}

	/** Updates the given object in the database. */
	public Object updateData(Object o) {
		return executeQuery((session) -> {
			session.update(o);
			return o;
		});
	}

	/** Executes the given procedure on the database. */
	private Object executeQuery(Function<Session, Object> query) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Object result = null;

		try {
			transaction = session.beginTransaction();
			result = query.apply(session);
			transaction.commit();
		}

		catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}
}
