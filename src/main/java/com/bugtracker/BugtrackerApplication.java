package com.bugtracker;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bugtracker.api.users.UserService;
import com.bugtracker.database.model.User;
import com.bugtracker.utils.StaticResourcesInfo;

@SpringBootApplication
public class BugtrackerApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	private final static Logger LOGGER = LoggerFactory.getLogger(BugtrackerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BugtrackerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (userService.getAllUsers().isEmpty()) {
			LOGGER.warn("Creating default user");
			User admin = new User("admin", "Domi", "Klein");
			userService.createUser(admin);
		}
	}

	@Bean
	public StaticResourcesInfo staticResourcesInfo() throws IOException {
		return new StaticResourcesInfo(this.getClass().getClassLoader());
	}
}
