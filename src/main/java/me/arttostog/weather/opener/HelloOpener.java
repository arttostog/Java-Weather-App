package me.arttostog.weather.opener;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.arttostog.weather.WeatherApplication;
import me.arttostog.weather.animation.HelloOpenerTransitionGuiAnimation;
import me.arttostog.weather.stage.HelloStageSettings;
import me.arttostog.weather.stage.StageSettings;

import java.io.IOException;

public class HelloOpener extends Opener {
	private static HelloOpener opener;

	HelloOpener(Stage stage) {
		super(stage, new FXMLLoader(WeatherApplication.class.getResource("hello.fxml")));
	}

	@Override
	public void open() throws IOException {
		stage.setScene(getScene());

		StageSettings settings = new HelloStageSettings();
		settings.setToBase(stage);
		settings.set(stage);

		stage.show();

		new HelloOpenerTransitionGuiAnimation(stage).startAnimation();
	}

	@Override
	Scene getScene() throws IOException {
		return new Scene(loader.load());
	}

	public static HelloOpener getOpener(Stage stage) {
		return opener == null ? (opener = new HelloOpener(stage)) : opener;
	}
}
