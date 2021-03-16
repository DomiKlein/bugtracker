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
		if (strings.length == 0) {
			return EMPTY_STRING;
		}

		List<String> stringToConcat = Arrays.asList(strings);
		StringBuilder result = new StringBuilder(stringToConcat.remove(0));

		for (String string : strings) {
			result.append(separator).append(string);
		}

		return result.toString();
	}
}
