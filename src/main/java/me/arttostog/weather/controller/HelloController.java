package me.arttostog.weather.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import me.arttostog.weather.user.User;
import me.arttostog.weather.weather.Data;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
	private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH");
	@FXML
	private VBox mainBox;
	@FXML
	private Label hello;
	@FXML
	private Label username;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		try {
			mainBox.setStyle(MainController.getBackgroundByWeather(Data.getData().getWeather().weather.main));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		hello.setText(getLabelByTime());
		username.setText(User.getUser().name + "!");

		setFontSize();
	}

	private String getLabelByTime() {
		return switch (Integer.parseInt(dtf.format(LocalTime.now()))) {
			case 22, 23, 0, 1, 2, 3 -> "Доброй ночи,";
			case 4, 5, 6, 7, 8, 9, 10, 11, 12 -> "Доброе утро,";
			case 13, 14, 15, 16, 17, 18 -> "Добрый день,";
			case 19, 20, 21 -> "Добрый вечер,";
			default -> "Доброго времени суток,";
		};
	}

	private void setFontSize() {
		int usernameLength = username.getText().length();
		if (usernameLength >= User.MAX_NAME_LENGTH / 2)
			username.setFont(Font.font(username.getFont().getSize() - (username.getText().length() / 3f)));
	}
}
