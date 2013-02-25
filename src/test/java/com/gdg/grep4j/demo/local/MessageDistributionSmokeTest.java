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
import static org.grep4j.core.fluent.Dictionary.executing;
import static org.grep4j.core.fluent.Dictionary.on;
import static org.grep4j.core.fluent.Dictionary.with;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.grep4j.core.result.GrepResults;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@Test
public class MessageDistributionSmokeTest {

	@BeforeTest
	public void triggerMessageDispatcher() {
		System.out
				.println("Producing and firing a Message(1546366) to downstream systems");
	}

	public void testProducerSentACREATEMessage() {
		
		/*
		 * I expect to have 1 message sent to the ESB
		 */
		assertThat(
				executing(
						grep(
							with(constantExpression("Message(1546366) Sent Successfully")), 
								on(producer))).
								totalLines(),is(1));

	}

	public void testESBReceivedAndSentACREATEMessage() {
		
		/*
		 * Remote ssh connection only one time.
		 */
		GrepResults globalEsbResult = grep(
				constantExpression("Message(1546366)"), on(esb));
		
		/*
		 * Post grep filtering: Only interested in the "Received" message
		 * I expect to have 1 message received by the ESB 
		 */
		assertThat(globalEsbResult.filterBy(constantExpression("Received"))
				.totalLines(), is(1));

		/*
		 * Post grep filtering: Only interested in the "Sent" messages
		 * I expect to have 1 message sent to the each of the 5 consumers 
		 */
		assertThat(globalEsbResult.filterBy(constantExpression("Sent"))
				.totalLines(), is(5));

	}

	public void testConsumersReceivedACREATEMessage() {
		
		/*
		 * I expect to see 1 message received for each of the 5 consumers
		 */
		assertThat(
				executing(
						grep(
							with(constantExpression("Message(1546366) Received")),
								on(consumer1, consumer2, consumer3, consumer4, consumer5)).
									totalLines()), is(5));

	}

}
