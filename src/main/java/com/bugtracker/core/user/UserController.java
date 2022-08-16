package com.bugtracker.core.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

	@ResponseBody
	@GetMapping("/{username}")
	public User findByUsername(@PathVariable String username) {
		return userService.findByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				"User with '" + username + "' username not found!"));
	}

}
