package me.arttostog.weather.request;

import me.arttostog.weather.openweathermapapi.data_2_5.DataAPIResponse;
import me.arttostog.weather.user.User;
import me.arttostog.weather.weather.Geo;

import java.io.IOException;

public class DataRequest extends Request<DataAPIResponse> {
	public DataRequest(Geo geo) {
		super("https://api.openweathermap.org/data/2.5/weather?lat=" + geo.getLat() +
				"&lon=" + geo.getLon() + "&lang=ru&units=metric&appid=" + User.getUser().apiKey);
	}

	@Override
	public DataAPIResponse getResponse() throws IOException {
		return gson.fromJson(create(), DataAPIResponse.class);
	}
}
