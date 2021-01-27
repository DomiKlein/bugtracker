package com.bugtracker.database.results.parsed;

import com.bugtracker.database.enums.ETables;

/** The Java class which represents a entry in the Users table. */
public class UserParsedQueryResult implements IParsedQueryResult {

	/** The unique ID of a user */
	private final int userId;

	/** The username */
	private final String username;

	/** The first name of the user */
	private final String firstName;

	/** The last name of the user */
	private final String lastName;

	public UserParsedQueryResult(int userId, String username, String firstName, String lastName) {
		this.userId = userId;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * @see #userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @see #userId
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @see #firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @see #lastName
	 */
	public String getLastName() {
		return lastName;
	}

	@Override
	public ETables getTable() {
		return ETables.USERS;
	}
}
