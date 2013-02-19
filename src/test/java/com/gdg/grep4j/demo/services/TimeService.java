package com.gdg.grep4j.demo.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeService {

	private static final Pattern timePattern = Pattern
			.compile("([0-9][0-9][0-9][0-9])-([0-9][0-9])-([0-9][0-9]) ([0-9][0-9]|2[0-3]):([0-9][0-9]):([0-9][0-9]),([0-9][0-9][0-9])");

	public static Long latency(long consumer1Time, long producerTime) {
		return consumer1Time - producerTime;
	}

	public static long extractTime(String text) {
		Matcher lm = timePattern.matcher(text);
		if (lm.find()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
			sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

			String inputString = lm.group();

			Date date = null;
			try {
				date = sdf.parse(inputString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return date.getTime();

		} else {
			throw new IllegalArgumentException("timePattern not found");
		}

	}

}
