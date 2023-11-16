package me.arttostog.weather.weather;

import com.google.gson.Gson;
import me.arttostog.weather.openweathermapapi.data_2_5.DataAPIResponse;
import me.arttostog.weather.request.ApiRequestCreator;
import me.arttostog.weather.user.User;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Data {
	private static Data data;
	private final Gson gson = new Gson();
	private DataAPIResponse weather;

	private Data() throws IOException {
		updateData();
	}

	public void updateData() throws IOException {
		weather = gson.fromJson(getResponse(new Geo(User.getUser().city)), DataAPIResponse.class);
	}

	private String getResponse(Geo geo) throws IOException {
		return new ApiRequestCreator(
				"https://api.openweathermap.org/data/2.5/weather?lat=" + geo.getLat() +
						"&lon=" + geo.getLon() + "&lang=ru&units=metric&appid=" + User.getUser().apiKey
		).create();
	}

	public DataAPIResponse getWeather() {
		return weather;
	}

	public List<String> getWeatherAsList() {
		return Arrays.asList(
				round(weather.main.temp) + "°",
				round(weather.main.temp_max) + "°",
				round(weather.main.temp_min) + "°",
				"Ощущается как: " + round(weather.main.feels_like) + "°",
				String.valueOf(User.getUser().city),
				getStatus(),
				weather.main.humidity + "%",
				weather.visibility + " км",
				String.valueOf(weather.main.pressure),
				round(weather.wind.speed) + "м/с",
				weather.weather.main
		);
	}

	private int round(float value) {
		return Math.round(value);
	}

	private String getStatus() {
		String str = weather.weather.description;
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	public static Data getData() throws IOException {
		return data == null ? (data = new Data()) : data;
	}
}
