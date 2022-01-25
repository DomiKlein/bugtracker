package com.bugtracker.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugtracker.database.model.User;
import com.bugtracker.database.repository.UsersRepository;

@Service
public class UserService {

	@Autowired
	private UsersRepository usersRepository;

	public User addUser() {
		int numberOfUsers = getAllUsers().size();
		User exampleUser = new User("test" + numberOfUsers, "Domi", "Klein");
		usersRepository.save(exampleUser);
		return exampleUser;
	}

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		for (User user : usersRepository.findAll()) {
			users.add(user);
		}
		return users;
	}

}
