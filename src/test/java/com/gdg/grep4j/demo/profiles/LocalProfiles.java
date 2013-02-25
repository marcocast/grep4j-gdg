package com.gdg.grep4j.demo.profiles;

import org.grep4j.core.model.Profile;
import org.grep4j.core.model.ProfileBuilder;

public class LocalProfiles {

	public static final Profile producer = createProducerProfile();
	public static final Profile esb = createEsbProfile();
	public static final Profile consumer1 = createConsumer1Profile();
	public static final Profile consumer2 = createConsumer2Profile();
	public static final Profile consumer3 = createConsumer3Profile();
	public static final Profile consumer4 = createConsumer4Profile();
	public static final Profile consumer5 = createConsumer5Profile();

	private static Profile createProducerProfile() {
		String resourcePath = LocalProfiles.class.getClassLoader()
				.getResource("producer/server.log").getPath();
		return createProfile("producer", resourcePath);
	}

	private static Profile createEsbProfile() {
		String resourcePath = LocalProfiles.class.getClassLoader()
				.getResource("esb/server.log").getPath();
		return createProfile("esb", resourcePath);
	}

	private static Profile createConsumer1Profile() {
		String resourcePath = LocalProfiles.class.getClassLoader()
				.getResource("consumer1/server.log").getPath();
		return createProfile("consumer1", resourcePath);
	}

	private static Profile createConsumer2Profile() {
		String resourcePath = LocalProfiles.class.getClassLoader()
				.getResource("consumer2/server.log").getPath();
		return createProfile("consumer2", resourcePath);
	}

	private static Profile createConsumer3Profile() {
		String resourcePath = LocalProfiles.class.getClassLoader()
				.getResource("consumer3/server.log").getPath();
		return createProfile("consumer3", resourcePath);
	}

	private static Profile createConsumer4Profile() {
		String resourcePath = LocalProfiles.class.getClassLoader()
				.getResource("consumer4/server.log").getPath();
		return createProfile("consumer4", resourcePath);
	}

	private static Profile createConsumer5Profile() {
		String resourcePath = LocalProfiles.class.getClassLoader()
				.getResource("consumer5/server.log").getPath();
		return createProfile("consumer5", resourcePath);
	}

	private static Profile createProfile(String profileName, String resourcePath) {
		return ProfileBuilder.newBuilder().name(profileName)
				.filePath(resourcePath).onLocalhost().build();
	}

}
