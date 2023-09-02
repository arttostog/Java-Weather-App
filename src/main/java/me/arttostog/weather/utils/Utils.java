package me.arttostog.weather.utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Utils {
	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH");
	public static String GetLabelByTime() {
		return switch (Integer.parseInt(dtf.format(LocalTime.now()))) {
			case 22, 23, 0, 1, 2, 3, 4 -> "Доброй ночи,";
			case 5, 6, 7, 8, 9, 10, 11, 12 -> "Доброе утро,";
			case 13, 14, 15, 16, 17, 18 -> "Добрый день,";
			case 19, 20, 21 -> "Добрый вечер,";
			default -> "Доброго времени суток,";
		};
	}

	public static String GetBackgroundByWeather(String weather) {
		return switch (weather) {
			case "Clear" -> "-fx-background-color: radial-gradient(center 100% -10% , radius 100% , Yellow, DeepSkyBlue);";
			case "Clouds" -> "-fx-background-color: linear-gradient(to bottom, CornflowerBlue, LightSkyBlue);";
			case "Rain", "Drizzle", "Thunderstorm" -> "-fx-background-color: linear-gradient(to bottom, CornflowerBlue, RoyalBlue);";
			case "Snow" -> "-fx-background-color: LightSkyBlue";
			default -> "-fx-background-color: LightSteelBlue";
		};
	}
}
