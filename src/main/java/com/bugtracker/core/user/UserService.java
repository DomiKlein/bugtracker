package com.bugtracker.core.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UsersRepository usersRepository;

	public User createUser(User user) {
		return usersRepository.save(user);
	}

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		for (User user : usersRepository.findAll()) {
			users.add(user);
		}
		return users;
	}

	public Optional<User> findByUsername(String username) {
		return usersRepository.findByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = findByUsername(username);
		if (user.isEmpty()) {
			throw new UsernameNotFoundException(String.format("User: '%s' not found", username));
		}
		return UserUtils.createUserDetails(user.get());
	}
}
