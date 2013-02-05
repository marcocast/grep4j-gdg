package com.gdg.grep4j.demo;

import static org.grep4j.core.Grep4j.grep;
import static org.grep4j.core.Grep4j.regularExpression;
import static org.grep4j.core.fluent.Dictionary.on;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.net.URL;

import org.grep4j.core.model.Profile;
import org.grep4j.core.model.ProfileBuilder;
import org.testng.annotations.Test;

import com.gdg.grep4j.demo.profiles.CredentialLoader;

public class TheTest {
	private static final String USER = CredentialLoader.getInstance().getTestEnvironmentUser();;
	private static final String PASSWORD = CredentialLoader.getInstance().getTestEnvironmentPassword();;

	@Test
	public void testRemoteMessageDispatch() {

		Profile remoteProfile = ProfileBuilder.newBuilder().name("profilename")
				.filePath("/opt/ops/logs/jboss/phase-pmu-handler/server.log").onRemotehost("url")
				.credentials(USER, PASSWORD).build();

		assertThat(grep(regularExpression("ERROR"), on(remoteProfile)).totalLines(), is(448));

	}

	@Test
	public void testLocalMessageDispatch() {
		URL url = TheTest.class.getClassLoader().getResource("credentials.properties");
		String resourcePath = url.getPath();
		Profile remoteProfile = ProfileBuilder.newBuilder().name("phase-pmu-handler")
				.filePath(resourcePath).onLocalhost().build();

		assertThat(grep(regularExpression("test"), on(remoteProfile)).totalLines(), is(2));

	}

}
