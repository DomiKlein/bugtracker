package com.bugtracker.utils;

public class StringUtils {

	public static final String EMPTY_STRING = "";

	public static String concat(String separator, String... strings) {
		String result = "";
		boolean isFirst = true;

		for (String string : strings) {
			if (isFirst) {
				isFirst = false;
				result = string;
				continue;
			}

			result = result + ", " + string;
		}

		return result;
	}
}
