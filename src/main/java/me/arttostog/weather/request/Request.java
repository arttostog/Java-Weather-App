package me.arttostog.weather.request;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import java.io.IOException;

public abstract class Request <T> {
	final Gson gson = new Gson();
	private final String url;

	Request(String Url) {
		this.url = Url;
	}

	String create() throws IOException{
		try (Response response = new OkHttpClient().newCall(new okhttp3.Request.Builder().url(url).build()).execute()) {
			if (response.body() == null) return "";
			return response.body().string().replaceAll("[\\[\\]]", "");
		}
	}

	public abstract T getResponse() throws IOException;
}