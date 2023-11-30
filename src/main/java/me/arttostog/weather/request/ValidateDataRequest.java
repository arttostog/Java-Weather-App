package me.arttostog.weather.request;

import com.google.gson.JsonParser;

import java.io.IOException;

public class ValidateDataRequest extends Request<Boolean> {
	public ValidateDataRequest(String City, String Token) {
		super("https://api.openweathermap.org/geo/1.0/direct?q=" + City + "&limit=1&appid=" + Token);
	}

	@Override
	public Boolean getResponse() throws IOException {
		return JsonParser.parseString(create()).getAsJsonObject().get("cod") != null;
	}
}
