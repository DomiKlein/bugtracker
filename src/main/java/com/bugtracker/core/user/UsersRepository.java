package com.bugtracker.core.user;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<User, Integer> {
	Optional<User> findByUsername(String username);
}
