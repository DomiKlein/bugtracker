package com.bugtracker.database.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.bugtracker.database.model.User;

public interface UsersRepository extends CrudRepository<User, Integer> {
	Optional<User> findByUsername(String username);
}
