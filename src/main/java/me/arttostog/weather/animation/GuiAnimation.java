package me.arttostog.weather.animation;

import javafx.animation.Timeline;

public abstract class GuiAnimation {
	private final Timeline animation;

	public GuiAnimation(Timeline animation) {
		this.animation = animation;
	}

	public void startAnimation() {
		animation.play();
	}
}
