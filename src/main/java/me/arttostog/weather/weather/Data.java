package me.arttostog.weather.weather;

import me.arttostog.weather.openweathermapapi.data_2_5.DataAPIResponse;
import me.arttostog.weather.request.DataRequest;
import me.arttostog.weather.user.User;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Data {
	private static Data data;
	private final DataRequest dataRequest;
	private DataAPIResponse weather;

	private Data() throws IOException {
		dataRequest = new DataRequest(new Geo());
		updateData();
	}

	public void updateData() throws IOException {
		weather = dataRequest.getResponse();
	}

	public String getWeatherName() {
		return weather.weather.main;
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
				round(weather.wind.speed) + "м/с"
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
