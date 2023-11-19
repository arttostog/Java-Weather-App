package me.arttostog.weather.stage;

import javafx.application.Platform;
import javafx.stage.Stage;

public class MainStageSettings extends StageSettings {
	public MainStageSettings(Stage stage) {
		super(stage);
	}

	@Override
	public void set() {
		stage.setOnCloseRequest(windowEvent -> {
			stage.hide();
			Platform.exit();
		});
	}
}
