package me.arttostog.weather.request;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestRequest extends Request<Boolean> {
	public TestRequest() {
		super("https://www.google.com/");
	}

	@Override
	public Boolean getResponse() throws IOException {
		return !create().isEmpty();
	}

	@Test
	public void checkRequest() throws IOException {
		Assert.assertTrue(getResponse());
	}
}