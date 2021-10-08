package com.bugtracker.utils;

import java.util.Arrays;
import java.util.List;

/** Utils for {@link String}s. */
public class StringUtils {

	/**
	 * An empty string. Use this when you want to make explicit that we used an
	 * empty string on purpose and is not a placeholder or something else.
	 */
	public static final String EMPTY_STRING = "";

	/**
	 * Concats all given {@code strings} together with the given {@code separator}.
	 */
	public static String concat(String separator, String... strings) {
		return concat(separator, Arrays.asList(strings));
	}

	/**
	 * Concats all given {@code strings} together with the given {@code separator}.
	 */
	public static String concat(String separator, List<String> strings) {
		if (strings.isEmpty()) {
			return EMPTY_STRING;
		}

		StringBuilder result = new StringBuilder(strings.remove(0));

		for (String string : strings) {
			result.append(separator).append(string);
		}

		return result.toString();
	}
}
