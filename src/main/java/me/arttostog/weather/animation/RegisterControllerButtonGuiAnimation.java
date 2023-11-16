package me.arttostog.weather.animation;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class RegisterControllerButtonGuiAnimation extends GuiAnimation {
	public RegisterControllerButtonGuiAnimation(Button button) {
		super(new Timeline(new KeyFrame(Duration.millis(0.1), event -> {
			button.setDisable(true);
			button.setStyle("-fx-background-color: gray; -fx-border-color: gray; -fx-opacity: 1.0;");
			PauseTransition pause = new PauseTransition(Duration.millis(500));
			pause.setOnFinished(e -> {
				button.setDisable(false);
				button.setStyle("-fx-background-color: LightGray; -fx-border-color: gray;");
			});
			pause.play();
		})));
	}
}
