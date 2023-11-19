package me.arttostog.weather.stage;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import me.arttostog.weather.WeatherApplication;

import java.util.Objects;

public abstract class StageSettings {
	final Stage stage;

	public StageSettings(Stage stage) {
		this.stage = stage;
	}
	public abstract void set();
	public void setToDefault() {
		stage.getIcons().add(new Image(Objects.requireNonNull(WeatherApplication.class.getResourceAsStream("icon.png"))));
		stage.setResizable(false);
		stage.setOnCloseRequest(windowEvent -> Platform.exit());
		stage.setTitle("Погода");
	}
}