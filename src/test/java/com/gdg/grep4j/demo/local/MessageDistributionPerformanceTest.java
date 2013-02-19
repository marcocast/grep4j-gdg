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

import org.grep4j.core.result.GrepResults;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@Test
public class MessageDistributionPerformanceTest {

	private long producerTime = 0;

	private GrepResults consumersResult;

	@BeforeTest
	public void extractProducerTime() {
		GrepResults producerResult = grep(
				regularExpression("Sent(.*)980238924(.*)CREATE"), on(producer));
		producerTime = extractTime(producerResult.toString());

		consumersResult = grep(
				regularExpression("Received(.*)980238924(.*)CREATE"),
				on(consumer1, consumer2, consumer3, consumer4, consumer5));
	}

	public void testConsumer1Latency() {

		long consumer1Time = extractTime(consumersResult.filterOnProfile(consumer1).toString());
		assertThat(latency(consumer1Time, producerTime), is(132470l));
	}

	public void testConsumer2Latency() {

		long consumer2Time = extractTime(consumersResult.filterOnProfile(consumer2).toString());
		assertThat(latency(consumer2Time, producerTime), is(12434l));

	}

	public void testConsumer3Latency() {

		long consumer3Time = extractTime(consumersResult.filterOnProfile(consumer3).toString());
		assertThat(latency(consumer3Time, producerTime), is(12435l));

	}

	public void testConsumer4Latency() {

		long consumer4Time = extractTime(consumersResult.filterOnProfile(consumer4).toString());
		assertThat(latency(consumer4Time, producerTime), is(132470l));

	}

	public void testConsumer5Latency() {

		long consumer5Time = extractTime(consumersResult.filterOnProfile(consumer5).toString());
		assertThat(latency(consumer5Time, producerTime), is(12431l));
	}

}
