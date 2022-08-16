package com.bugtracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bugtracker.core.user.User;
import com.bugtracker.core.user.UserRole;
import com.bugtracker.core.user.UserService;

@SpringBootApplication
public class BugtrackerApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private final static Logger LOGGER = LoggerFactory.getLogger(BugtrackerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BugtrackerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (userService.getAllUsers().isEmpty()) {
			LOGGER.warn("Creating default user");
			User admin = new User("admin", passwordEncoder.encode("123"), "Domi", "Klein", UserRole.ADMIN);
			userService.createUser(admin);
		}
	}
}
