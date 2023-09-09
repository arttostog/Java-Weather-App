package me.arttostog.weather.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import me.arttostog.weather.WeatherApplication;
import me.arttostog.weather.weather.Data;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
	@FXML
	private Text Temp;
	@FXML
	private Text MaxTemp;
	@FXML
	private Text MinTemp;
	@FXML
	private Text TempFeelsLike;
	@FXML
	private Text Name;
	@FXML
	private Text Status;
	@FXML
	private Text Humidity;
	@FXML
	private Text Visibility;
	@FXML
	private Text Pressure;
	@FXML
	private Text Wind;
	@FXML
	private VBox MainBox;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		try {
			this.update();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void update() throws IOException {
		updateScene(new Data());
		setStatusFontSize();
	}

	private void updateScene(Data data) {
		Temp.setText(Math.round(data.getWeather().main.temp) + "°");
		MaxTemp.setText(Math.round(data.getWeather().main.temp_max) + "°");
		MinTemp.setText(Math.round(data.getWeather().main.temp_min) + "°");
		TempFeelsLike.setText("Ощущается как: " + Math.round(data.getWeather().main.feels_like) + "°");
		Name.setText(WeatherApplication.user.City);
		Status.setText(getStatus(data));
		Humidity.setText(data.getWeather().main.humidity+ "%");
		Visibility.setText(data.getWeather().visibility + " км");
		Pressure.setText(data.getWeather().main.pressure + "");
		Wind.setText(Math.round(data.getWeather().wind.speed) + " м/с");
		MainBox.setStyle(getBackgroundByWeather(data.getWeather().weather.main));
	}

	private String getStatus(Data data) {
		String str = data.getWeather().weather.description;
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	private void setStatusFontSize() {
		Font DefaultFontSize = Font.font(18);
		int WeatherStatusLength = Status.getText().length();
		if (WeatherStatusLength > 18) {
			Status.setFont(Font.font(DefaultFontSize.getSize() - (WeatherStatusLength - 18) * 0.90f));
		}
	}

	public static String getBackgroundByWeather(String weather) {
		return switch (weather) {
			case "Clear" -> "-fx-background-color: radial-gradient(center 100% -10% , radius 100% , Yellow, DeepSkyBlue);";
			case "Clouds" -> "-fx-background-color: linear-gradient(to bottom, CornflowerBlue, LightSkyBlue);";
			case "Rain", "Drizzle", "Thunderstorm" -> "-fx-background-color: linear-gradient(to bottom, CornflowerBlue, RoyalBlue);";
			case "Snow" -> "-fx-background-color: LightSkyBlue";
			default -> "-fx-background-color: LightSteelBlue";
		};
	}
}
