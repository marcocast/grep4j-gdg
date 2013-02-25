package com.gdg.grep4j.demo.profiles;

import org.grep4j.core.model.Profile;
import org.grep4j.core.model.ProfileBuilder;
import com.gdg.grep4j.demo.profiles.ConfLoader;

public class RemoteProfiles {

	private static final String URL = ConfLoader.getInstance().getTestUrl();
	private static final int PORT = ConfLoader.getInstance().getTestPort();
	private static final String USER = ConfLoader.getInstance()
			.getTestEnvironmentUser();
	private static final String PASSWORD = ConfLoader.getInstance()
			.getTestEnvironmentPassword();
	private static final String LOG_ROOT = ConfLoader.getInstance()
			.getTestLogRoot();

	public static final Profile producer = createProducerProfile();
	public static final Profile esb = createEsbProfile();
	public static final Profile consumer1 = createConsumer1Profile();
	public static final Profile consumer2 = createConsumer2Profile();
	public static final Profile consumer3 = createConsumer3Profile();
	public static final Profile consumer4 = createConsumer4Profile();
	public static final Profile consumer5 = createConsumer5Profile();

	private static Profile createProducerProfile() {
		return createProfile("producer", LOG_ROOT + "/producer/server.log", URL);
	}

	private static Profile createEsbProfile() {

		return createProfile("esb", LOG_ROOT + "/esb/server.log", URL);
	}

	private static Profile createConsumer1Profile() {

		return createProfile("consumer1", LOG_ROOT + "/consumer1/server.log",
				URL);
	}

	private static Profile createConsumer2Profile() {

		return createProfile("consumer2", LOG_ROOT + "/consumer2/server.log",
				URL);
	}

	private static Profile createConsumer3Profile() {

		return createProfile("consumer3", LOG_ROOT + "/consumer3/server.log",
				URL);
	}

	private static Profile createConsumer4Profile() {

		return createProfile("consumer4", LOG_ROOT + "/consumer4/server.log",
				URL);
	}

	private static Profile createConsumer5Profile() {

		return createProfile("consumer5", LOG_ROOT + "/consumer5/server.log",
				URL);
	}

	private static Profile createProfile(String profileName,
			String resourcePath, String url) {
		return ProfileBuilder.newBuilder().name(profileName)
				.filePath(resourcePath).onRemotehost(url)
				.credentials(USER, PASSWORD).sshPort(PORT).build();
	}

}
