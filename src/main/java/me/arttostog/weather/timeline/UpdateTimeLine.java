package me.arttostog.weather.timeline;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.util.Duration;
import me.arttostog.weather.controller.MainController;
import me.arttostog.weather.weather.Data;

import java.io.IOException;

public class UpdateTimeLine extends TimeLine {
	public UpdateTimeLine(FXMLLoader loader) {
		super(new Timeline(new KeyFrame(Duration.seconds(15), event -> {
			try {
				Data.getData().updateData();
				((MainController) loader.getController()).updateScene();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		})));
	}

	@Override
	public void startTimeLine() {
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
	}
}
