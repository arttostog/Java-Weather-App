package me.arttostog.weather.request;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Objects;

public class RequestCreator {
	private final String Url;

	public RequestCreator(String Url) {
		this.Url = Url;
	}

	public String Create() throws IOException {
		Request request = new Request.Builder().url(Url).build();
		try (Response response = new OkHttpClient().newCall(request).execute()) {
			return Objects.requireNonNull(response.body()).string();
		}
	}
}
