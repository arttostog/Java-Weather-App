package me.arttostog.weather.opener;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.arttostog.weather.WeatherApplication;
import me.arttostog.weather.stage.MainStageSettings;
import me.arttostog.weather.timeline.UpdateTimeLine;

import java.io.IOException;

public class MainOpener extends Opener {
	public MainOpener(Stage stage) {
		super(stage, new MainStageSettings(stage), false,
				new FXMLLoader(WeatherApplication.class.getResource("main.fxml")));
	}

	@Override
	public void open() throws IOException {
		stage.setScene(getScene());
		new UpdateTimeLine(loader).startTimeLine();
	}

	@Override
	Scene getScene() throws IOException {
		return new Scene(loader.load());
	}
}
