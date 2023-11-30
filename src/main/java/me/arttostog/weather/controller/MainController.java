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
	private Text tempText;
	@FXML
	private Text maxTempText;
	@FXML
	private Text minTempText;
	@FXML
	private Text tempFeelsLikeText;
	@FXML
	private Text cityText;
	@FXML
	private Text statusText;
	private double defaultStatusFontSize;
	@FXML
	private Text humidityText;
	@FXML
	private Text visibilityText;
	@FXML
	private Text pressureText;
	@FXML
	private Text windText;
	@FXML
	private VBox mainBox;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		defaultStatusFontSize = statusText.getFont().getSize();
		try {
			this.updateScene();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void updateScene() throws IOException {
		List<Text> textList = Arrays.asList(
				tempText, maxTempText, minTempText, tempFeelsLikeText, cityText, statusText, humidityText, visibilityText, pressureText, windText
		);
		Data data = Data.getData();
		List<String> dataAsList = data.getWeatherAsList();

		for (int i = 0; i < textList.size(); i++) {
			textList.get(i).setText(dataAsList.get(i));
		}
		mainBox.setStyle(data.getBackgroundByWeather());
		updateStatusFontSize();
	}

	private void updateStatusFontSize() {
		int weatherStatusLength = statusText.getText().length();
		if (weatherStatusLength > defaultStatusFontSize / 2)
			statusText.setFont(Font.font(defaultStatusFontSize - weatherStatusLength / 10f));
	}
}