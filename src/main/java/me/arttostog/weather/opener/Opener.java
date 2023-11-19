package me.arttostog.weather.opener;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.arttostog.weather.stage.StageSettings;

import java.io.IOException;

public abstract class Opener {
	final Stage stage;
	final FXMLLoader loader;

	Opener(Stage stage, StageSettings stageSettings, boolean setStageSettingsToDefault, FXMLLoader loader) {
		this.stage = stage;

		if (setStageSettingsToDefault) stageSettings.setToDefault();
		stageSettings.set();

		this.loader = loader;
	}

	public abstract void open() throws IOException;

	abstract Scene getScene() throws IOException;
}