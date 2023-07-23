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
		String response = RequestCreator.Create("https://api.openweathermap.org/geo/1.0/direct?q=" + City + "&limit=1&appid=" + WeatherApplication.user.Token);

		JsonObject jsonObject = JsonParser.parseString(response.replace("[", "").replace("]", "")).getAsJsonObject();
		JsonObject name = JsonParser.parseString(jsonObject.get("local_names").toString()).getAsJsonObject();

		this.Lon = jsonObject.get("lon").getAsDouble();
		this.Lat = jsonObject.get("lat").getAsDouble();
		this.City = name.get("ru").getAsString();
	}
}
