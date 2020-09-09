package com.bugtracker.init;

import org.apache.log4j.BasicConfigurator;

import com.bugtracker.database.DatabaseConnection;

public class BugtrackerStarter {

	public static void main(String[] args) {
		// Configure logger
		BasicConfigurator.configure();

		DatabaseConnection databaseConnection = new DatabaseConnection();
		databaseConnection.run();
	}
}
