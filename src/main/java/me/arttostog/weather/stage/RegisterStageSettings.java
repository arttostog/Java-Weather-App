package me.arttostog.weather.stage;

import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RegisterStageSettings extends StageSettings {
	public RegisterStageSettings(Stage stage) {
		super(stage);
	}

	@Override
	public void set() {
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.setOnCloseRequest(windowEvent -> {
			stage.hide();
			Platform.exit();
		});
	}
}