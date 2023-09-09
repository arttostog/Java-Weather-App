package me.arttostog.weather.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import me.arttostog.weather.weather.Weather;

import java.net.URL;
import java.util.ResourceBundle;

public class WeatherController implements Initializable {
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
		this.Update();
	}

	public void Update() {
		Temp.setText(Weather.Temp + "°");
		TempFeelsLike.setText("Ощущается как: " + Weather.TempFeelsLike + "°");
		MaxTemp.setText(Weather.MaxTemp + "°");
		MinTemp.setText(Weather.MinTemp + "°");
		Humidity.setText(Weather.Humidity + "%");
		Name.setText(Weather.Name);
		Visibility.setText(Weather.Visibility + " км");
		Pressure.setText(Weather.Pressure + "");
		Wind.setText(Weather.Wind + " м/с");

		MainBox.setStyle(GetBackgroundByWeather(Weather.WeatherMain));

		Font DefaultFontSize = Font.font(18);
		int WeatherStatusLength = Weather.Status.length();
		if (WeatherStatusLength > 18) {
			Status.setFont(Font.font(DefaultFontSize.getSize() - (WeatherStatusLength - 18) * 0.90f));
		}
		Status.setText(Weather.Status);
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
