package com.bugtracker.utils;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bugtracker.database.model.User;

/** Utils for {@link User}s */
public class UserUtils {

	/**
	 * Creates {@link UserDetails} for the given user.
	 */
	public static UserDetails createUserDetails(User user) {
		List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole()));
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				authorities);
	}

}
