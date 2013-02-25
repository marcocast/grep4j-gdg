package com.gdg.grep4j.demo;

import static com.gdg.grep4j.demo.profiles.LocalProfiles.consumer1;
import static com.gdg.grep4j.demo.profiles.LocalProfiles.consumer2;
import static com.gdg.grep4j.demo.profiles.LocalProfiles.consumer3;
import static com.gdg.grep4j.demo.profiles.LocalProfiles.consumer4;
import static com.gdg.grep4j.demo.profiles.LocalProfiles.consumer5;
import static com.gdg.grep4j.demo.profiles.LocalProfiles.producer;
import static com.gdg.grep4j.demo.services.TimeService.extractTime;
import static org.grep4j.core.Grep4j.constantExpression;
import static org.grep4j.core.Grep4j.grep;
import static org.grep4j.core.fluent.Dictionary.on;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.number.OrderingComparison.lessThan;
import static org.junit.Assert.assertThat;

import org.grep4j.core.result.GrepResults;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@Test
public class MessageDistributionPerformanceTest {


	private static final long MAX_ACCETABLE_LATENCY = 400L;

	private long producerTime = 0;

	private GrepResults consumersResults;
	
	@BeforeTest
	public void triggerMessageDispatcher() {
		System.out
				.println("Producing and firing a Message(1546366) to downstream systems");
	}
	
	@BeforeTest
	public void extractProducerTime() {
		
		GrepResults producerResult = grep(
				constantExpression("Message(1546366) Sent Successfully"),
				on(producer));
		producerTime = extractTime(producerResult.toString());
	}
	
	@BeforeTest
	public void grepConsumerLogs() {
				
		consumersResults = grep(
				constantExpression("Message(1546366) Received"),
				on(consumer1, consumer2, consumer3, consumer4, consumer5));
	}

	public void testConsumer1Latency() {
 
		long consumer1Time = extractTime(consumersResults.filterOnProfile(consumer1).toString());
		assertThat((consumer1Time - producerTime),
				is(lessThan(MAX_ACCETABLE_LATENCY)));
	}

	public void testConsumer2Latency() {

		long consumer2Time = extractTime(consumersResults.filterOnProfile(consumer2).toString());
		assertThat((consumer2Time - producerTime),
				is(lessThan(MAX_ACCETABLE_LATENCY)));

	}

	public void testConsumer3Latency() {

		long consumer3Time = extractTime(consumersResults.filterOnProfile(consumer3).toString());
		assertThat((consumer3Time - producerTime),
				is(lessThan(MAX_ACCETABLE_LATENCY)));

	}

	public void testConsumer4Latency() {

		long consumer4Time = extractTime(consumersResults.filterOnProfile(consumer4).toString());
		assertThat((consumer4Time - producerTime),
				is(lessThan(MAX_ACCETABLE_LATENCY)));

	}

	public void testConsumer5Latency() {

		long consumer5Time = extractTime(consumersResults.filterOnProfile(consumer5).toString());
		assertThat((consumer5Time - producerTime),
				is(lessThan(MAX_ACCETABLE_LATENCY)));
	}

}
