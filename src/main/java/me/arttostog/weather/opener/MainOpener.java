package me.arttostog.weather.opener;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import me.arttostog.weather.WeatherApplication;
import me.arttostog.weather.controller.MainController;
import me.arttostog.weather.stage.MainStageSettings;
import me.arttostog.weather.weather.Data;

import java.io.IOException;

public class MainOpener extends Opener {
	private static MainOpener opener;

	private final Timeline update = new Timeline(new KeyFrame(Duration.seconds(15), event -> {
		try {
			Data.getData().updateData();
			((MainController) loader.getController()).updateScene();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}));

	MainOpener(Stage stage) {
		super(stage, new FXMLLoader(WeatherApplication.class.getResource("main.fxml")));
	}

	@Override
	public void open() throws IOException {
		new MainStageSettings().set(stage);

		stage.setScene(getScene());

		update.play();
		update.setCycleCount(Animation.INDEFINITE);
	}

	@Override
	Scene getScene() throws IOException {
		return new Scene(loader.load());
	}

	public static MainOpener getOpener(Stage stage) {
		return opener == null ? (opener = new MainOpener(stage)) : opener;
	}
}
