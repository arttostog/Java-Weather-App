package me.arttostog.weather.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import me.arttostog.weather.WeatherApplication;
import me.arttostog.weather.config.Config;
import me.arttostog.weather.request.RequestCreator;
import me.arttostog.weather.user.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
	@FXML
	private Text error;
	@FXML
	private TextField name;
	@FXML
	private TextField city;
	@FXML
	private TextField token;
	@FXML
	public Button button;

	@FXML
	private void submit(ActionEvent event) throws IOException {
		startButtonPressAnimation();

		if (!checkFields()) {
			return;
		}

		Config.saveUser(WeatherApplication.user = new User(name.getText(), city.getText(), token.getText()));

		Stage stage  = (Stage) ((Node)  event.getSource()).getScene().getWindow();
		stage.hide();
	}

	private void startButtonPressAnimation() {
		new Thread(() -> {
			try {
				button.setStyle("-fx-background-color: gray; -fx-border-color: gray;");
				Thread.sleep(125);
				button.setStyle("-fx-background-color: LightGray; -fx-border-color: gray;");
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}).start();
	}

	private boolean checkFields() throws IOException {
		if (name.getText().isEmpty() || city.getText().isEmpty() || token.getText().isEmpty()
				|| name.getText().length() > 25 || test(token.getText(), city.getText())) {
			error.setVisible(true);
			return false;
		}
		return true;
	}

	private boolean test(String Token, String City) throws IOException {
		return new RequestCreator(
				new StringBuilder("https://api.openweathermap.org/geo/1.0/direct?q=")
						.append(City)
						.append("&limit=1&appid=")
						.append(Token)
						.toString())
				.create()
				.replaceAll("[\\[\\]]", "")
				.isEmpty();
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		error.setVisible(false);
	}
}
