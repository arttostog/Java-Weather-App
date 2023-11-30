package me.arttostog.weather.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import me.arttostog.weather.time.Time;
import me.arttostog.weather.user.User;
import me.arttostog.weather.weather.Data;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
	@FXML
	private VBox mainBox;
	@FXML
	private Label helloLabel;
	@FXML
	private Label usernameLabel;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		try {
			mainBox.setStyle(Data.getData().getBackgroundByWeather());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		helloLabel.setText(Time.getLabelByTime());
		usernameLabel.setText(User.getUser().name + "!");

		setFontSize();
	}

	private void setFontSize() {
		int usernameLength = usernameLabel.getText().length();
		if (usernameLength >= User.MAX_NAME_LENGTH / 2)
			usernameLabel.setFont(Font.font(usernameLabel.getFont().getSize() - (usernameLabel.getText().length() / 3f)));
	}
}
