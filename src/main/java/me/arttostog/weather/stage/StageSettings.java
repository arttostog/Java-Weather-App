package me.arttostog.weather.stage;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import me.arttostog.weather.WeatherApplication;

import java.util.Objects;

public abstract class StageSettings {
	public abstract void set(Stage stage);
	public void setToBase(Stage stage) {
		stage.getIcons().add(new Image(Objects.requireNonNull(WeatherApplication.class.getResourceAsStream("icon.png"))));
		stage.setResizable(false);
		stage.setOnCloseRequest(windowEvent -> Platform.exit());
		stage.setTitle("Погода");
	}
}
