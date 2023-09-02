package me.arttostog.weather.weather;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.arttostog.weather.WeatherApplication;
import me.arttostog.weather.config.Config;
import me.arttostog.weather.request.RequestCreator;

import java.io.IOException;

public class Weather {
	public static String Name = "";
	public static int Temp = 0;
	public static int TempFeelsLike = 0;
	public static int MaxTemp = 0;
	public static int MinTemp = 0;
	public static String WeatherMain = "";
	public static String Status = "";
	public static int Wind = 0;
	public static int Humidity = 0;
	public static int Pressure = 0;
	public static int Visibility = 0;

	public static void Update() throws IOException {
		GetLonLat gll = new GetLonLat(Config.GetUser().City());

		String url = new StringBuilder("https://api.openweathermap.org/data/2.5/weather?lat=")
				.append(gll.Lat)
				.append("&lon=")
				.append(gll.Lon)
				.append("&lang=ru&units=metric&appid=")
				.append(WeatherApplication.user.APIKey())
				.toString();
		String response = new RequestCreator(url).Create();

		JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
		JsonObject main = JsonParser.parseString(jsonObject.get("main").toString()).getAsJsonObject();
		JsonObject weather = JsonParser.parseString(jsonObject.get("weather").toString().replaceAll("[\\[\\]]", "")
				.split(",\\{")[0])
				.getAsJsonObject();
		JsonObject wind = JsonParser.parseString(jsonObject.get("wind").toString()).getAsJsonObject();

		Name = gll.City;
		Temp = main.get("temp").getAsInt();
		TempFeelsLike = main.get("temp").getAsInt();
		MaxTemp = main.get("temp_max").getAsInt();
		MinTemp = main.get("temp_min").getAsInt();
		WeatherMain = weather.get("main").getAsString();

		String description = weather.get("description").getAsString();
		Status = description.replaceFirst(String.valueOf(description.charAt(0)), String.valueOf(description.charAt(0)).toUpperCase());

		Humidity = main.get("humidity").getAsInt();
		Pressure = Math.round(main.get("pressure").getAsInt() * 0.75f);
		Visibility = Math.round(jsonObject.get("visibility").getAsInt() / 1000f);
		Wind = wind.get("speed").getAsInt();
	}
}
