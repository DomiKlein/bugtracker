package com.bugtracker.database.parser;

import java.awt.*;
import java.util.Date;

import org.assertj.core.util.Preconditions;

import com.bugtracker.database.results.IQueryResult;

/**
 * Represents the results of a query on a imaginary database.
 * <p>
 * For the i-th row:
 * <ul>
 * <li>{@link #getInt(int)} returns: "{i}"</li>
 * <li>{@link #getString(int)} returns: "Test {i}"</li>
 * <li>{@link #getTimestamp(int)} returns the date with {i} milliseconds after
 * 1.1.1970</li>
 * <li>{@link #getString(int)} returns the color which has the RGB code {i}</li>
 * </ul>
 */
public class TestQueryResult implements IQueryResult {

	/**
	 * The number of results which should be returned
	 */
	private final int numberOfResults;

	/**
	 * Counts how many results have been parsed yet
	 */
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