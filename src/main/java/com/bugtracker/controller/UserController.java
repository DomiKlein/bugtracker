package com.bugtracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bugtracker.database.model.User;
import com.bugtracker.services.UserService;

@Controller
@RequestMapping(path = "/api/users")
public class UserController {
	@Autowired
	private UserService userService;

	@ResponseBody
	@GetMapping
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@ResponseBody
	@PostMapping
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}

}
