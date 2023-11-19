package me.arttostog.weather.request;

import me.arttostog.weather.openweathermapapi.geo_1_0.GeoAPIResponse;
import me.arttostog.weather.user.User;

import java.io.IOException;

public class GeoRequest extends Request<GeoAPIResponse> {
	public GeoRequest() {
		super("https://api.openweathermap.org/geo/1.0/direct?q=" + User.getUser().city + "&limit=1&appid=" + User.getUser().apiKey);
	}

	@Override
	public GeoAPIResponse getResponse() throws IOException {
		return gson.fromJson(create(), GeoAPIResponse.class);
	}
}
