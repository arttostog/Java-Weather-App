package me.arttostog.weather.utills;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LabelByTime {
	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH");
	public static String GetLabel() {
		return switch (Integer.parseInt(dtf.format(LocalTime.now()))) {
			case 22, 23, 0, 1, 2, 3, 4 -> "Доброй ночи,";
			case 5, 6, 7, 8, 9, 10, 11, 12 -> "Доброе утро,";
			case 13, 14, 15, 16, 17, 18 -> "Добрый день,";
			case 19, 20, 21 -> "Добрый вечер,";
			default -> "Доброго времени суток,";
		};
	}
}
