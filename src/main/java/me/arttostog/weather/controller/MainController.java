package me.arttostog.weather.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import me.arttostog.weather.weather.Data;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
	@FXML
	private Text temp;
	@FXML
	private Text maxTemp;
	@FXML
	private Text minTemp;
	@FXML
	private Text tempFeelsLike;
	@FXML
	private Text city;
	@FXML
	private Text status;
	private double defaultStatusFontSize;
	@FXML
	private Text humidity;
	@FXML
	private Text visibility;
	@FXML
	private Text pressure;
	@FXML
	private Text wind;
	@FXML
	private VBox mainBox;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		defaultStatusFontSize = status.getFont().getSize();
		try {
			this.updateScene();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void updateScene() throws IOException {
		List<Text> textList = Arrays.asList(
				temp, maxTemp, minTemp, tempFeelsLike, city, status, humidity, visibility, pressure, wind
		);
		List<String> dataAsList = Data.getData().getWeatherAsList();
		for (int i = 0; i < textList.size(); i++) {
			textList.get(i).setText(dataAsList.get(i));
		}
		mainBox.setStyle(getBackgroundByWeather(dataAsList.get(dataAsList.size() - 1)));
		updateStatusFontSize();
	}

	private void updateStatusFontSize() {
		int weatherStatusLength = status.getText().length();
		if (weatherStatusLength > defaultStatusFontSize)
			status.setFont(Font.font(defaultStatusFontSize - (weatherStatusLength - defaultStatusFontSize) * 0.9f));
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