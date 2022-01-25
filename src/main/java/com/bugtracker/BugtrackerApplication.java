package com.bugtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @EnableJpaRepositories(basePackages = "com.bugtracker.database.repository")
// @EntityScan(basePackages = "com.bugtracker.database.model")
// @ComponentScan(basePackages = "com.bugtracker.controller")
@SpringBootApplication
public class BugtrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BugtrackerApplication.class, args);

		// Handle uncaught exception properly
		// Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionLogger());
	}

}
