package com.bugtracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bugtracker.database.model.User;
import com.bugtracker.services.UserService;

@Controller
@RequestMapping(path = "/api/users")
public class UserController {
	@Autowired
	private UserService userService;

	@ResponseBody
	@GetMapping(path = "/add")
	public User addUser() {
		return userService.addUser();
	}

	@ResponseBody
	@GetMapping
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
}
