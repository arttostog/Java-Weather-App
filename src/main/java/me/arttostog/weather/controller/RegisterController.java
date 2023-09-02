package me.arttostog.weather.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import me.arttostog.weather.WeatherApplication;
import me.arttostog.weather.config.Config;
import me.arttostog.weather.request.RequestCreator;
import me.arttostog.weather.user.User;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
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

	private static double xOffset = 0;
	private static double yOffset = 0;

	public static void Login() throws IOException {
		if (Config.isExist()) {
			WeatherApplication.user = Config.GetUser();
			return;
		}
		RegisterController.Open();
	}

	private static void Open() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(WeatherApplication.class.getResource("register.fxml"));

		Scene scene = new Scene(fxmlLoader.load(), Color.TRANSPARENT);
		Stage stage = new Stage();

		stage.getIcons().add(new Image(Objects.requireNonNull(WeatherApplication.class.getResourceAsStream("icon.png"))));
		stage.setResizable(false);
		stage.setTitle("Погода");
		stage.setScene(scene);
		stage.initStyle(StageStyle.TRANSPARENT);

		stage.setOnCloseRequest(windowEvent -> Platform.exit());
		scene.setOnMousePressed(event -> {
			xOffset = stage.getX() - event.getScreenX();
			yOffset = stage.getY() - event.getScreenY();
		});
		scene.setOnMouseDragged(event -> {
			stage.setX(event.getScreenX() + xOffset);
			stage.setY(event.getScreenY() + yOffset);
		});

		stage.showAndWait();
	}

	@FXML
	private void Submit(ActionEvent event) throws IOException {
		if (name.getText().isEmpty() || city.getText().isEmpty() || token.getText().isEmpty() || name.getText().length() > 25 || Test(token.getText(), city.getText())) {
			error.setVisible(true);
			return;
		}

		User user = new User(name.getText(), city.getText(), token.getText());
		WeatherApplication.user = user;
		Config.SaveUser(user);

		Node source = (Node)  event.getSource();
		Stage stage  = (Stage) source.getScene().getWindow();
		stage.hide();
	}

	private static boolean Test(String Token, String City) throws IOException {
		String url = new StringBuilder("https://api.openweathermap.org/geo/1.0/direct?q=")
				.append(City)
				.append("&limit=1&appid=")
				.append(Token)
				.toString();
		return new RequestCreator(url).Create().replaceAll("[\\[\\]]", "").isEmpty();
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		error.setVisible(false);
	}
}
