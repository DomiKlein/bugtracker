package com.bugtracker.database.repository;

import org.springframework.data.repository.CrudRepository;

import com.bugtracker.database.model.User;

public interface UsersRepository extends CrudRepository<User, Integer> {
}
