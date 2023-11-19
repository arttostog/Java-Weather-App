package me.arttostog.weather;

import javafx.application.Application;
import javafx.stage.Stage;
import me.arttostog.weather.config.Config;
import me.arttostog.weather.opener.HelloOpener;
import me.arttostog.weather.opener.RegisterOpener;

import java.io.IOException;

public class WeatherApplication extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		RegisterOpener.getInstance().open();
		if (!Config.getConfig().isExist()) return;
		new HelloOpener(stage).open();
	}

	public static void main(String[] args) {
		launch();
	}
}