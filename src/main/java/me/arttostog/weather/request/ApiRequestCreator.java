package me.arttostog.weather.request;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class ApiRequestCreator {
	private final String url;

	public ApiRequestCreator(String Url) {
		this.url = Url;
	}

	public String create() throws IOException{
		try (Response response = new OkHttpClient().newCall(new Request.Builder().url(url).build()).execute()) {
			if (response.body() == null) return "";
			return response.body().string().replaceAll("[\\[\\]]", "");
		}
	}
}
