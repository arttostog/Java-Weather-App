package me.arttostog.weather.weather;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.arttostog.weather.WeatherApplication;
import me.arttostog.weather.request.RequestCreator;

import java.io.IOException;

public class GetLonLat {
	public String City;
	public double Lon;
	public double Lat;

	public GetLonLat(String City) throws IOException {
		String url = new StringBuilder("https://api.openweathermap.org/geo/1.0/direct?q=")
				.append(City)
				.append("&limit=1&appid=")
				.append(WeatherApplication.user.APIKey())
				.toString();
		String response = new RequestCreator(url).Create();

		JsonObject jsonObject = JsonParser.parseString(response.replaceAll("[\\[\\]]", "")).getAsJsonObject();
		JsonObject name = JsonParser.parseString(jsonObject.get("local_names").toString()).getAsJsonObject();

		this.Lon = jsonObject.get("lon").getAsDouble();
		this.Lat = jsonObject.get("lat").getAsDouble();
		this.City = name.get("ru").getAsString();
	}
}
