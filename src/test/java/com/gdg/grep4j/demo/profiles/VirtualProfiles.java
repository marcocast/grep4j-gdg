package com.gdg.grep4j.demo.profiles;

import org.grep4j.core.model.Profile;
import org.grep4j.core.model.ProfileBuilder;
import com.gdg.grep4j.demo.profiles.CredentialLoader;

public class VirtualProfiles {

	private static final String URL = CredentialLoader.getInstance()
			.getVirtualTestUrl();
	private static final int PORT = CredentialLoader.getInstance()
			.getVirtualTestPort();
	private static final String USER = CredentialLoader.getInstance()
			.getVirtualTestEnvironmentUser();
	private static final String PASSWORD = CredentialLoader.getInstance()
			.getVirtualTestEnvironmentPassword();

	public static final Profile producer = createProducerProfile();
	public static final Profile esb = createEsbProfile();
	public static final Profile consumer1 = createConsumer1Profile();
	public static final Profile consumer2 = createConsumer2Profile();
	public static final Profile consumer3 = createConsumer3Profile();
	public static final Profile consumer4 = createConsumer4Profile();
	public static final Profile consumer5 = createConsumer5Profile();

	private static Profile createProducerProfile() {
		return createProfile("producer", "/home/user/gdg/resources/producer/server.log", URL);
	}

	private static Profile createEsbProfile() {

		return createProfile("esb",
				"/home/user/gdg/resources/esb/server.log", URL);
	}

	private static Profile createConsumer1Profile() {

		return createProfile("consumer1",
				"/home/user/gdg/resources/consumer1/server.log", URL);
	}

	private static Profile createConsumer2Profile() {

		return createProfile("consumer2",
				"/home/user/gdg/resources/consumer2/server.log", URL);
	}

	private static Profile createConsumer3Profile() {

		return createProfile("consumer3",
				"/home/user/gdg/resources/consumer3/server.log", URL);
	}

	private static Profile createConsumer4Profile() {

		return createProfile("consumer4",
				"/home/user/gdg/resources/consumer4/server.log", URL);
	}

	private static Profile createConsumer5Profile() {

		return createProfile("consumer5",
				"/home/user/gdg/resources/consumer5/server.log", URL);
	}

	private static Profile createProfile(String profileName,
			String resourcePath, String url) {
		return ProfileBuilder.newBuilder().name(profileName)
				.filePath(resourcePath).onRemotehost(url)
				.credentials(USER, PASSWORD).sshPort(PORT).build();
	}

}
