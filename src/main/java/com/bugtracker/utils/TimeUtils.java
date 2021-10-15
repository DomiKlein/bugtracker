package com.bugtracker.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.TimeZone;

/** Utils which deal with time. */
public class TimeUtils {

	/** Returns the systems time zone. */
	public static String getSystemTimeZone() {
		return TimeZone.getTimeZone(getSystemTimeZoneId()).getID();
	}

	/** Returns the systems time zone offset from UTC. */
	public static String getSystemTimeZoneOffsetFromUTC() {
		return getSystemTimeZoneId().getRules().getStandardOffset(Instant.now()).getId();
	}

	private static ZoneId getSystemTimeZoneId() {
		return ZonedDateTime.now(ZoneId.systemDefault()).getZone();
	}
}
