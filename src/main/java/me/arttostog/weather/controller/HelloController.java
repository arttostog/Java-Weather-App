package me.arttostog.weather.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import me.arttostog.weather.WeatherApplication;
import me.arttostog.weather.utils.Utils;
import me.arttostog.weather.weather.Weather;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
	@FXML
	private VBox MainBox;
	@FXML
	private Label Hello;
	@FXML
	private Label User;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		MainBox.setStyle(Utils.GetBackgroundByWeather(Weather.WeatherMain));
		Hello.setText(Utils.GetLabelByTime());

		String name = WeatherApplication.user.Name();
		int NameLength = name.length();
		int MaxNameLength = 17;

		if (NameLength > MaxNameLength) {
			User.setFont(Font.font(24 - (NameLength - 17)));
		}

		User.setText(name + "!");
	}
}
