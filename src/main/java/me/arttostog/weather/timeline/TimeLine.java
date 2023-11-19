package me.arttostog.weather.timeline;

import javafx.animation.Timeline;

public abstract class TimeLine {
	final Timeline timeline;

	public TimeLine(Timeline timeline) {
		this.timeline = timeline;
	}

	public abstract void startTimeLine();
}
