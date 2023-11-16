package me.arttostog.weather.stage;

import javafx.application.Platform;
import javafx.stage.Stage;

public class MainStageSettings extends StageSettings {
	@Override
	public void set(Stage stage) {
		stage.setOnCloseRequest(windowEvent -> {
			stage.hide();
			Platform.exit();
		});
	}
}
