package com.gdg.grep4j.demo.local;

import static com.gdg.grep4j.demo.profiles.LocalProfiles.consumer1;
import static com.gdg.grep4j.demo.profiles.LocalProfiles.consumer2;
import static com.gdg.grep4j.demo.profiles.LocalProfiles.consumer3;
import static com.gdg.grep4j.demo.profiles.LocalProfiles.consumer4;
import static com.gdg.grep4j.demo.profiles.LocalProfiles.consumer5;
import static com.gdg.grep4j.demo.profiles.LocalProfiles.producer;
import static org.grep4j.core.Grep4j.grep;
import static org.grep4j.core.Grep4j.regularExpression;
import static org.grep4j.core.fluent.Dictionary.on;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.grep4j.core.result.GrepResults;
import org.testng.annotations.Test;

@Test
public class MessageDistributionPerformanceTest {

	private static final Pattern timePattern = Pattern
			.compile("([0-9][0-9][0-9][0-9])-([0-9][0-9])-([0-9][0-9]) ([0-9][0-9]|2[0-3]):([0-9][0-9]):([0-9][0-9]),([0-9][0-9][0-9])");

	public void testLocalProducerMessageDispatch() {

		GrepResults producerResult = grep(
				regularExpression("Sent(.*)980238924(.*)CREATE"), on(producer));
		GrepResults consumersResult = grep(
				regularExpression("Received(.*)980238924(.*)CREATE"),
				on(consumer1, consumer2, consumer3, consumer4, consumer5));

		long producerTime = extractTime(producerResult.toString());
		long consumer1Time = extractTime(consumersResult.filterOnProfile(
				consumer1).toString());
		long consumer2Time = extractTime(consumersResult.filterOnProfile(
				consumer2).toString());
		long consumer3Time = extractTime(consumersResult.filterOnProfile(
				consumer3).toString());
		long consumer4Time = extractTime(consumersResult.filterOnProfile(
				consumer4).toString());
		long consumer5Time = extractTime(consumersResult.filterOnProfile(
				consumer5).toString());

		assertThat(latency(consumer1Time, producerTime), is(132470l));
		assertThat(latency(consumer2Time, producerTime), is(12434l));
		assertThat(latency(consumer3Time, producerTime), is(12435l));
		assertThat(latency(consumer4Time, producerTime), is(132470l));
		assertThat(latency(consumer5Time, producerTime), is(12431l));
	}

	private Long latency(long consumer1Time, long producerTime) {
		return consumer1Time - producerTime;
	}

	private long extractTime(String text) {
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
			return 0;
		}

	}

}
