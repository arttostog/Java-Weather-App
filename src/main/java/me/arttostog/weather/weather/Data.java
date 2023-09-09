package me.arttostog.weather.weather;

import com.google.gson.Gson;
import me.arttostog.weather.WeatherApplication;
import me.arttostog.weather.config.Config;
import me.arttostog.weather.openweathermap.data_2_5.DataAPIResponse;
import me.arttostog.weather.request.RequestCreator;

import java.io.IOException;

public class Data {
	private final DataAPIResponse weather;

	public Data() throws IOException {
		weather = new Gson().fromJson(GetResponse(new Geo(Config.getUser().City))
				.replaceAll("[\\[\\]]", ""), DataAPIResponse.class);
	}

	private String GetResponse(Geo geo) throws IOException {
		return new RequestCreator(
				new StringBuilder("https://api.openweathermap.org/data/2.5/weather?lat=")
						.append(geo.getLat())
						.append("&lon=")
						.append(geo.getLon())
						.append("&lang=ru&units=metric&appid=")
						.append(WeatherApplication.user.APIKey)
						.toString()).create();
	}

	public DataAPIResponse getWeather() {
		return weather;
	}
}
