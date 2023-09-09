package me.arttostog.weather.weather;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.arttostog.weather.WeatherApplication;
import me.arttostog.weather.openweathermap.geo_1_0.GeoAPIResponse;
import me.arttostog.weather.request.RequestCreator;

import java.io.IOException;

public class Geo {
	private final double Lat;
	private final double Lon;

	public Geo(String city) throws IOException {
		GeoAPIResponse response = new Gson().fromJson(GetResponse(city)
				.replaceAll("[\\[\\]]", ""), GeoAPIResponse.class);

		this.Lat = response.lat;
		this.Lon = response.lon;
	}

	private String GetResponse(String city) throws IOException {
		return new RequestCreator(
				new StringBuilder("https://api.openweathermap.org/geo/1.0/direct?q=")
						.append(city)
						.append("&limit=1&appid=")
						.append(WeatherApplication.user.APIKey)
						.toString())
				.create().replaceAll("[\\[\\]]", "");
	}

	public double getLon() {
		return Lon;
	}

	public double getLat() {
		return Lat;
	}
}
