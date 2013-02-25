package com.gdg.grep4j.demo.local;

import static com.gdg.grep4j.demo.profiles.LocalProfiles.consumer1;
import static com.gdg.grep4j.demo.profiles.LocalProfiles.consumer2;
import static com.gdg.grep4j.demo.profiles.LocalProfiles.consumer3;
import static com.gdg.grep4j.demo.profiles.LocalProfiles.consumer4;
import static com.gdg.grep4j.demo.profiles.LocalProfiles.consumer5;
import static com.gdg.grep4j.demo.profiles.LocalProfiles.producer;
import static com.gdg.grep4j.demo.services.TimeService.extractTime;
import static com.gdg.grep4j.demo.services.TimeService.latency;

import static org.grep4j.core.Grep4j.grep;
import static org.grep4j.core.Grep4j.regularExpression;
import static org.grep4j.core.fluent.Dictionary.on;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.number.OrderingComparison.lessThan;

import org.grep4j.core.result.GrepResults;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@Test
public class MessageDistributionPerformanceTest {

	private final String ID_TO_CHECK = "1546366";

	private static final long MAX_ACCETABLE_LATENCY = 400l;

	private long producerTime = 0;

	private GrepResults consumersResult;

	@BeforeTest
	public void extractProducerTime() {
		GrepResults producerResult = grep(
				regularExpression("Sent(.*)CREATE(.*)" + ID_TO_CHECK),
				on(producer));
		producerTime = extractTime(producerResult.toString());

		consumersResult = grep(
				regularExpression("Received(.*)EVENT,(.*)CREATE(.*)"
						+ ID_TO_CHECK),
				on(consumer1, consumer2, consumer3, consumer4, consumer5));
	}

	public void testConsumer1Latency() {

		long consumer1Time = extractTime(consumersResult.filterOnProfile(
				consumer1).toString());
		assertThat(latency(consumer1Time, producerTime),
				is(lessThan(MAX_ACCETABLE_LATENCY)));
	}

	public void testConsumer2Latency() {

		long consumer2Time = extractTime(consumersResult.filterOnProfile(
				consumer2).toString());
		assertThat(latency(consumer2Time, producerTime),
				is(lessThan(MAX_ACCETABLE_LATENCY)));

	}

	public void testConsumer3Latency() {

		long consumer3Time = extractTime(consumersResult.filterOnProfile(
				consumer3).toString());
		assertThat(latency(consumer3Time, producerTime),
				is(lessThan(MAX_ACCETABLE_LATENCY)));

	}

	public void testConsumer4Latency() {

		long consumer4Time = extractTime(consumersResult.filterOnProfile(
				consumer4).toString());
		assertThat(latency(consumer4Time, producerTime),
				is(lessThan(MAX_ACCETABLE_LATENCY)));

	}

	public void testConsumer5Latency() {

		long consumer5Time = extractTime(consumersResult.filterOnProfile(
				consumer5).toString());
		assertThat(latency(consumer5Time, producerTime),
				is(lessThan(MAX_ACCETABLE_LATENCY)));
	}

}
