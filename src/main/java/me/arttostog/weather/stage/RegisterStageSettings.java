package me.arttostog.weather.stage;

import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RegisterStageSettings extends StageSettings {
	@Override
	public void set(Stage stage) {
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.setOnCloseRequest(windowEvent -> {
			stage.hide();
			Platform.exit();
		});
	}
}
