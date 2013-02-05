package com.gdg.grep4j.demo.profiles;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CredentialLoader {
	private final Properties prop;

	private static class SingletonHolder {
		public static final CredentialLoader INSTANCE = new CredentialLoader();
	}

	public static CredentialLoader getInstance() {
		return SingletonHolder.INSTANCE;
	}

	private CredentialLoader() {
		prop = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream stream = loader.getResourceAsStream("credentials.properties");
		try {
			prop.load(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getTestEnvironmentUser() {
		return prop.getProperty("test-environment.user");
	}

	public String getTestEnvironmentPassword() {
		return prop.getProperty("test-environment.password");
	}

}
