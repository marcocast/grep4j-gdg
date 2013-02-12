package com.gdg.grep4j.demo.remote;

import static com.gdg.grep4j.demo.profiles.RemoteProfiles.consumer1;
import static com.gdg.grep4j.demo.profiles.RemoteProfiles.consumer2;
import static com.gdg.grep4j.demo.profiles.RemoteProfiles.consumer3;
import static com.gdg.grep4j.demo.profiles.RemoteProfiles.consumer4;
import static com.gdg.grep4j.demo.profiles.RemoteProfiles.consumer5;
import static com.gdg.grep4j.demo.profiles.RemoteProfiles.esb;
import static com.gdg.grep4j.demo.profiles.RemoteProfiles.producer;
import static org.grep4j.core.Grep4j.grep;
import static org.grep4j.core.Grep4j.regularExpression;
import static org.grep4j.core.fluent.Dictionary.on;
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
				.println("Producing and firing a CREATE message to downstream systems with message id 980238924");
	}

	public void testRemoteProducerMessageDispatch() {

		assertThat(grep(regularExpression("980238924(.*)CREATE"), on(producer))
				.totalLines(), is(1));

	}

	public void testRemoteESBMessageDispatch() {

		GrepResults globalEsbResult = grep(
				regularExpression("980238924(.*)CREATE"), on(esb));

		assertThat(globalEsbResult.filterBy("Received").totalLines(), is(1));

		assertThat(globalEsbResult.filterBy("Sent").totalLines(), is(5));

	}

	public void testRemoteConsumerMessageDispatch() {

		assertThat(
				grep(
						regularExpression("Received(.*)980238924(.*)CREATE"),
						on(consumer1, consumer2, consumer3, consumer4,
								consumer5)).totalLines(), is(5));

	}

}
