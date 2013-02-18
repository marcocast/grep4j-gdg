package com.gdg.grep4j.demo.profiles;

import org.grep4j.core.model.Profile;
import org.grep4j.core.model.ProfileBuilder;
import com.gdg.grep4j.demo.profiles.CredentialLoader;

public class RemoteProfiles {

	private static final String URL = CredentialLoader.getInstance()
			.getTestUrl();
	private static final int PORT = CredentialLoader.getInstance()
			.getTestPort();
	private static final String USER = CredentialLoader.getInstance()
			.getTestEnvironmentUser();
	private static final String PASSWORD = CredentialLoader.getInstance()
			.getTestEnvironmentPassword();

	public static final Profile producer = createProducerProfile();
	public static final Profile esb = createEsbProfile();
	public static final Profile consumer1 = createConsumer1Profile();
	public static final Profile consumer2 = createConsumer2Profile();
	public static final Profile consumer3 = createConsumer3Profile();
	public static final Profile consumer4 = createConsumer4Profile();
	public static final Profile consumer5 = createConsumer5Profile();

	private static Profile createProducerProfile() {
		return createProfile("producer", "/home/grep4j/demo/resources/producer/server.log", URL);
	}

	private static Profile createEsbProfile() {

		return createProfile("esb",
				"/home/grep4j/demo/resources/esb/server.log", URL);
	}

	private static Profile createConsumer1Profile() {

		return createProfile("consumer1",
				"/home/grep4j/demo/resources/consumer1/server.log", URL);
	}

	private static Profile createConsumer2Profile() {

		return createProfile("consumer2",
				"/home/grep4j/demo/resources/consumer2/server.log", URL);
	}

	private static Profile createConsumer3Profile() {

		return createProfile("consumer3",
				"/home/grep4j/demo/resources/consumer3/server.log", URL);
	}

	private static Profile createConsumer4Profile() {

		return createProfile("consumer4",
				"/home/grep4j/demo/resources/consumer4/server.log", URL);
	}

	private static Profile createConsumer5Profile() {

		return createProfile("consumer5",
				"/home/grep4j/demo/resources/consumer5/server.log", URL);
	}

	private static Profile createProfile(String profileName,
			String resourcePath, String url) {
		return ProfileBuilder.newBuilder().name(profileName)
				.filePath(resourcePath).onRemotehost(url)
				.credentials(USER, PASSWORD).sshPort(PORT).build();
	}

}
