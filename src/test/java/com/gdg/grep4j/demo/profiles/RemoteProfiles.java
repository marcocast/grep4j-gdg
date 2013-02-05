package com.gdg.grep4j.demo.profiles;

import org.grep4j.core.model.Profile;
import org.grep4j.core.model.ProfileBuilder;
import com.gdg.grep4j.demo.profiles.CredentialLoader;

public class RemoteProfiles {

	private static final String USER = CredentialLoader.getInstance().getTestEnvironmentUser();
	private static final String PASSWORD = CredentialLoader.getInstance().getTestEnvironmentPassword();

	public static final Profile producer = createProducerProfile();
	public static final Profile esb = createEsbProfile();
	public static final Profile consumer1 = createConsumer1Profile();
	public static final Profile consumer2 = createConsumer2Profile();
	public static final Profile consumer3 = createConsumer3Profile();
	public static final Profile consumer4 = createConsumer4Profile();
	public static final Profile consumer5 = createConsumer5Profile();

	private static Profile createProducerProfile() {
		String resourcePath = "";
		return createProfile("producer", resourcePath, "URL");
	}

	private static Profile createEsbProfile() {
		String resourcePath = RemoteProfiles.class.getClassLoader().getResource("esb/server.log").getPath();
		return createProfile("producer", resourcePath, "URL");
	}

	private static Profile createConsumer1Profile() {
		String resourcePath = RemoteProfiles.class.getClassLoader().getResource("consumer1/server.log").getPath();
		return createProfile("producer", resourcePath, "URL");
	}

	private static Profile createConsumer2Profile() {
		String resourcePath = RemoteProfiles.class.getClassLoader().getResource("consumer2/server.log").getPath();
		return createProfile("producer", resourcePath, "URL");
	}

	private static Profile createConsumer3Profile() {
		String resourcePath = RemoteProfiles.class.getClassLoader().getResource("consumer3/server.log").getPath();
		return createProfile("producer", resourcePath, "URL");
	}

	private static Profile createConsumer4Profile() {
		String resourcePath = RemoteProfiles.class.getClassLoader().getResource("consumer4/server.log").getPath();
		return createProfile("producer", resourcePath, "URL");
	}

	private static Profile createConsumer5Profile() {
		String resourcePath = RemoteProfiles.class.getClassLoader().getResource("consumer5/server.log").getPath();
		return createProfile("producer", resourcePath, "URL");
	}

	private static Profile createProfile(String profileName, String resourcePath, String url) {
		return ProfileBuilder.newBuilder().name(profileName)
				.filePath(resourcePath).onRemotehost(url)
				.credentials(USER, PASSWORD).build();
	}

}
