package com.gdg.grep4j.demo.local;

import static com.gdg.grep4j.demo.profiles.LocalProfiles.consumer1;
import static com.gdg.grep4j.demo.profiles.LocalProfiles.consumer2;
import static com.gdg.grep4j.demo.profiles.LocalProfiles.consumer3;
import static com.gdg.grep4j.demo.profiles.LocalProfiles.consumer4;
import static com.gdg.grep4j.demo.profiles.LocalProfiles.consumer5;
import static com.gdg.grep4j.demo.profiles.LocalProfiles.esb;
import static com.gdg.grep4j.demo.profiles.LocalProfiles.producer;
import static org.grep4j.core.Grep4j.constantExpression;
import static org.grep4j.core.Grep4j.grep;
import static org.grep4j.core.Grep4j.regularExpression;
import static org.grep4j.core.fluent.Dictionary.on;
import static org.grep4j.core.fluent.Dictionary.executing;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.grep4j.core.result.GrepResults;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@Test
public class MessageDistributionSmokeTest {

	private final String ID_TO_CHECK = "1546366";

	@BeforeTest
	public void triggerMessageDispatcher() {
		System.out
				.println("Producing and firing a CREATE message to downstream systems with message id 1546366");
	}

	public void testProducerDispatchACREATEMessage() {

		assertThat(
				executing(
						grep(regularExpression("Sent(.*)CREATE(.*)"
								+ ID_TO_CHECK), on(producer))).totalLines(),
				is(1));

	}

	public void testESBReceiveAndDispatchACREATEMessage() {

		GrepResults globalEsbResult = grep(
				regularExpression("EVENT,(.*)CREATE(.*)" + ID_TO_CHECK),
				on(esb));

		assertThat(globalEsbResult.filterBy(constantExpression("Received"))
				.totalLines(), is(1));

		assertThat(globalEsbResult.filterBy(constantExpression("Sent"))
				.totalLines(), is(5));

	}

	public void testConsumersReceiveACREATEMessage() {

		assertThat(
				executing(grep(
						regularExpression("Received(.*)EVENT,(.*)CREATE(.*)"
								+ ID_TO_CHECK),
						on(consumer1, consumer2, consumer3, consumer4,
								consumer5)).totalLines()), is(5));

	}

}
