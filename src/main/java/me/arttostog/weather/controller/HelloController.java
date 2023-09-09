package me.arttostog.weather.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import me.arttostog.weather.WeatherApplication;
import me.arttostog.weather.weather.Data;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
	private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH");
	@FXML
	private VBox MainBox;
	@FXML
	private Label Hello;
	@FXML
	private Label User;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		try {
			MainBox.setStyle(MainController.getBackgroundByWeather(new Data().getWeather().weather.main));
			Hello.setText(getLabelByTime());
			User.setText(WeatherApplication.user.Name + "!");
			setFontSize();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void setFontSize() {
		int NameLength = User.getText().length();
		int MaxNameLength = 17;

		if (NameLength > MaxNameLength) {
			User.setFont(Font.font(24 - (NameLength - 17)));
		}
	}

	private String getLabelByTime() {
		return switch (Integer.parseInt(dtf.format(LocalTime.now()))) {
			case 22, 23, 0, 1, 2, 3, 4 -> "Доброй ночи,";
			case 5, 6, 7, 8, 9, 10, 11, 12 -> "Доброе утро,";
			case 13, 14, 15, 16, 17, 18 -> "Добрый день,";
			case 19, 20, 21 -> "Добрый вечер,";
			default -> "Доброго времени суток,";
		};
	}
}
