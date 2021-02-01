package com.bugtracker.testdatabase.results;

import java.awt.*;
import java.util.Date;

import org.assertj.core.util.Preconditions;

import com.bugtracker.database.results.IQueryResult;

/** Represents the results of a query on a imaginary database. */
public class TestQueryResult implements IQueryResult {

	/** The number of results which should be returned */
	private int numberOfResults;

	/** Counts how many results have been parsed yet */
	private int counter;

	public TestQueryResult(int numberOfResults) {
		Preconditions.checkArgument(numberOfResults >= 0, "The number of results must be equal or greater than zero.");
		this.numberOfResults = numberOfResults;
		this.counter = 0;
	}

	@Override
	public boolean next() {
		counter++;
		return counter <= numberOfResults;
	}

	@Override
	public Integer getInt(int columnIndex) {
		return counter;
	}

	@Override
	public String getString(int columnIndex) {
		return "Test " + counter;
	}

	@Override
	public Date getTimestamp(int columnIndex) {
		return new Date(counter);
	}

	@Override
	public Color getColor(int columnIndex) {
		return new Color(counter);
	}
}