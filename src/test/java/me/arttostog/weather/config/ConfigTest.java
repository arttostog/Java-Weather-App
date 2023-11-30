package me.arttostog.weather.config;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ConfigTest {
	@Test
	public void checkConfig() throws IOException {
		Config config = Config.getConfig();
		if (config.isExist()) {
			Assert.assertNotNull(config.getUser());
		}
	}
}
