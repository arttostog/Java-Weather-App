package me.arttostog.weather.stage;

import javafx.application.Platform;
import javafx.stage.Stage;

public class HelloStageSettings extends StageSettings {
	public HelloStageSettings(Stage stage) {
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
