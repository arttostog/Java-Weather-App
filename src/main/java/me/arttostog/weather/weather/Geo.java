package me.arttostog.weather.weather;

import com.google.gson.Gson;
import me.arttostog.weather.openweathermapapi.geo_1_0.GeoAPIResponse;
import me.arttostog.weather.request.ApiRequestCreator;
import me.arttostog.weather.user.User;

import java.io.IOException;

public class Geo {
	private final double Lat;
	private final double Lon;

	public Geo(String city) throws IOException {
		GeoAPIResponse response = new Gson().fromJson(GetResponse(city), GeoAPIResponse.class);
		this.Lat = response.lat;
		this.Lon = response.lon;
	}

	private String GetResponse(String city) throws IOException {
		return new ApiRequestCreator(
				"https://api.openweathermap.org/geo/1.0/direct?q=" + city + "&limit=1&appid=" + User.getUser().apiKey
		).create();
	}

	public double getLon() {
		return Lon;
	}

	public double getLat() {
		return Lat;
	}
}
