package me.arttostog.weather.opener;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.arttostog.weather.WeatherApplication;
import me.arttostog.weather.timeline.TransitionTimeLine;
import me.arttostog.weather.stage.HelloStageSettings;

import java.io.IOException;

public class HelloOpener extends Opener {
	public HelloOpener(Stage stage) {
		super(stage, new HelloStageSettings(stage), true,
				new FXMLLoader(WeatherApplication.class.getResource("hello.fxml")));
	}

	@Override
	public void open() throws IOException {
		stage.setScene(getScene());

		stage.show();

		new TransitionTimeLine(stage).startTimeLine();
	}

	@Override
	Scene getScene() throws IOException {
		return new Scene(loader.load());
	}
}
