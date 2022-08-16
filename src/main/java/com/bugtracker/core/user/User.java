package com.bugtracker.core.user;

import javax.persistence.*;

import com.bugtracker.core.Tables;
import com.bugtracker.utils.ExportToTypeScript;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class which represents a user.
 * <p>
 * <strong>Note: User should not implement UserDetails by themselves as this
 * would lead to unnecessary TypeScript definitions.</strong>
 */
@Entity
@Table(name = Tables.USERS)
@ExportToTypeScript
public class User {

	/** Column name of the user id. */
	public static final String USER_ID_COLUMN_NAME = "userId";
	/** Column name of the username. */
	public static final String USERNAME_COLUMN_NAME = "username";
	/** Column name of the password. */
	public static final String PASSWORD_COLUMN_NAME = "password";
	/** Column name of the first name. */
	public static final String FIRST_NAME_COLUMN_NAME = "firstName";
	/** Column name of the last name. */
	public static final String LAST_NAME_COLUMN_NAME = "lastName";
	/** Column name of the role. */
	public static final String ROLE_COLUMN_NAME = "role";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = USER_ID_COLUMN_NAME, unique = true, nullable = false)
	@JsonProperty(value = USER_ID_COLUMN_NAME)
	private Integer userId;

	@Column(name = USERNAME_COLUMN_NAME, unique = true, nullable = false)
	@JsonProperty(value = USERNAME_COLUMN_NAME, required = true)
	private String username;

	@Column(name = PASSWORD_COLUMN_NAME, nullable = false)
	@JsonProperty(value = PASSWORD_COLUMN_NAME, required = true, access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	@Column(name = FIRST_NAME_COLUMN_NAME, nullable = false)
	@JsonProperty(value = FIRST_NAME_COLUMN_NAME, required = true)
	private String firstName;

	@Column(name = LAST_NAME_COLUMN_NAME, nullable = false)
	@JsonProperty(value = LAST_NAME_COLUMN_NAME, required = true)
	private String lastName;

	@Column(name = ROLE_COLUMN_NAME, nullable = false)
	@JsonProperty(value = ROLE_COLUMN_NAME, required = true)
	private String role;

	protected User() {
		// Required for fetching entries form database
	}

	public User(String username, String password, String firstName, String lastName, String role) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
	}

	/**
	 * @see #username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/** @see #password */
	public void setPassword(String password) {
		this.password = password;
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

	/** @see #role */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @see #userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @see #username
	 */
	public String getUsername() {
		return username;
	}

	/** @see #password */
	public String getPassword() {
		return password;
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

	/** @see #role */
	public String getRole() {
		return role;
	}
}
