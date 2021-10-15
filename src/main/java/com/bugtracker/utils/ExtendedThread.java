package com.bugtracker.utils;

/** Thread with extended functionality (like properly terminating it). */
public abstract class ExtendedThread extends Thread {

	/** Value to check whether the thread is still running. */
	private volatile boolean running = true;

	@Override
	public final void run() {
		setup();
		while (running) {
			execute();
		}
	}

	/** Terminates the thread and properly closes remaining connections. */
	public void terminate() {
		running = false;
		cleanUp();
	}

	/** Executes necessary setup tasks before the thread starts. */
	protected abstract void setup();

	/** Executes the tasks of the thread. */
	protected abstract void execute();

	/** Cleans up the environment (e.g. close remaining connections). */
	protected abstract void cleanUp();
}
