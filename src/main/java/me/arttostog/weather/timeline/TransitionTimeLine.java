package me.arttostog.weather.timeline;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;
import me.arttostog.weather.opener.MainOpener;

import java.io.IOException;

public class TransitionTimeLine extends TimeLine {
	public TransitionTimeLine(Stage stage) {
		super(new Timeline(new KeyFrame(Duration.seconds(5), event -> {
			try {
				new MainOpener(stage).open();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		})));
	}

	@Override
	public void startTimeLine() {
		timeline.play();
	}
}
