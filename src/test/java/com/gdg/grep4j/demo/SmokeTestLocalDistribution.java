package com.gdg.grep4j.demo;

import static com.gdg.grep4j.demo.profiles.LocalProfiles.*;
import static org.grep4j.core.Grep4j.grep;
import static org.grep4j.core.Grep4j.regularExpression;
import static org.grep4j.core.fluent.Dictionary.on;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@Test
public class SmokeTestLocalDistribution {

	@BeforeTest
	public void triggerMessageDispatcher() {
		System.out.println("Producing and firing a CREATE message to downstream systems with message id 980238924");
	}

	public void testLocalProducerMessageDispatch() {

		assertThat(grep(regularExpression("980238924(.*)CREATE"), on(producer)).totalLines(), is(1));

	}

	public void testLocalESBMessageDispatch() {

		assertThat(grep(regularExpression("980238924(.*)CREATE"), on(esb)).totalLines(), is(10));

	}

	public void testLocalConsumerMessageDispatch() {

		assertThat(grep(regularExpression("980238924(.*)CREATE"), on(consumer1, consumer2, consumer3, consumer4, consumer5)).totalLines(), is(16));

	}

}
