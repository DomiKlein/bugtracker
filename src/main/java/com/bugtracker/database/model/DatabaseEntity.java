package com.bugtracker.database.model;

public abstract class DatabaseEntity {

	/** Returns the id (aka primary key) of the object. */
	public abstract Object getId();

	public boolean isDetached() {
		return getId() != null;
	}

	/**
	 * Override this method in case of problems like <a href=
	 * "https://stackoverflow.com/questions/47146081/detached-entity-passed-to-persist-when-using-composite-key-with-objects">this
	 * one</a>.
	 */
	public boolean forceMerge() {
		return false;
	}
}
