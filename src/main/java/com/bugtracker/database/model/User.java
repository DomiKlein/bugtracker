package com.bugtracker.database.model;

import javax.persistence.*;

/** Class which represents a user. */
@Entity
@Table(name = "users")
public class User {

	/** Column name of the user id. */
	public static final String USER_ID_COLUMN_NAME = "userId";
	/** Column name of the username. */
	public static final String USERNAME_COLUMN_NAME = "username";
	/** Column name of the first name. */
	public static final String FIRST_NAME_COLUMN_NAME = "firstName";
	/** Column name of the last name. */
	public static final String LAST_NAME_COLUMN_NAME = "lastName";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = USER_ID_COLUMN_NAME, nullable = false)
	private int userId;

	@Column(name = USERNAME_COLUMN_NAME, nullable = false)
	private String username;

	@Column(name = FIRST_NAME_COLUMN_NAME, nullable = false)
	private String firstName;

	@Column(name = LAST_NAME_COLUMN_NAME, nullable = false)
	private String lastName;

	/**
	 * @see #username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @see #firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @see #lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @see #userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @see #username
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
}
