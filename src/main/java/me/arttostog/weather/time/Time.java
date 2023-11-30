package me.arttostog.weather.time;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Time {
	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH");

	public static String getLabelByTime() {
		return switch (Integer.parseInt(dtf.format(LocalTime.now()))) {
			case 22, 23, 0, 1, 2, 3 -> "Доброй ночи,";
			case 4, 5, 6, 7, 8, 9, 10, 11, 12 -> "Доброе утро,";
			case 13, 14, 15, 16, 17, 18 -> "Добрый день,";
			case 19, 20, 21 -> "Добрый вечер,";
			default -> "Доброго времени суток,";
		};
	}
}
