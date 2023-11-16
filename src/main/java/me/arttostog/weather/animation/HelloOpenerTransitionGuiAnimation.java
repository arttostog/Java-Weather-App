package me.arttostog.weather.animation;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;
import me.arttostog.weather.opener.MainOpener;

import java.io.IOException;

public class HelloOpenerTransitionGuiAnimation extends GuiAnimation {
	public HelloOpenerTransitionGuiAnimation(Stage stage) {
		super(new Timeline(new KeyFrame(Duration.seconds(5), event -> {
			try {
				MainOpener.getOpener(stage).open();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		})));
	}
}
