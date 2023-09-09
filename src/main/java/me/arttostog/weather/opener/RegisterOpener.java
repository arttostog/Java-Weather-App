package me.arttostog.weather.opener;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import me.arttostog.weather.WeatherApplication;
import me.arttostog.weather.config.Config;

import java.io.IOException;
import java.util.Objects;

public class RegisterOpener {
	private static double xOffset = 0;
	private static double yOffset = 0;

	public static void login() throws IOException {
		if (Config.isExist()) {
			WeatherApplication.user = Config.getUser();
			return;
		}
		open();
	}

	private static void open() throws IOException {
		Stage stage = getStage();
		stage.showAndWait();
	}

	private static Stage getStage() throws IOException {
		Stage stage = new Stage();

		stage.getIcons().add(new Image(Objects.requireNonNull(WeatherApplication.class.getResourceAsStream("icon.png"))));
		stage.setResizable(false);
		stage.setTitle("Погода");
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.setOnCloseRequest(windowEvent -> Platform.exit());
		stage.setScene(getScene(stage));
		return stage;
	}

	private static Scene getScene(Stage stage) throws IOException {
		Scene scene = new Scene(new FXMLLoader(WeatherApplication.class.getResource("register.fxml")).load(), Color.TRANSPARENT);

		scene.setOnMousePressed(event -> {
			xOffset = stage.getX() - event.getScreenX();
			yOffset = stage.getY() - event.getScreenY();
		});
		scene.setOnMouseDragged(event -> {
			stage.setX(event.getScreenX() + xOffset);
			stage.setY(event.getScreenY() + yOffset);
		});

		return scene;
	}
}
