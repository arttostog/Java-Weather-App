package me.arttostog.weather;

import javafx.application.Application;
import javafx.stage.Stage;
import me.arttostog.weather.config.Config;
import me.arttostog.weather.opener.MainOpener;
import me.arttostog.weather.opener.RegisterOpener;
import me.arttostog.weather.user.User;

import java.io.IOException;

public class WeatherApplication extends Application {
	public static User user;
	public static Stage st;

	@Override
	public void start(Stage stage) throws IOException {
		st = stage;

		RegisterOpener.login();

		if (Config.isExist()) {
			MainOpener.showMainStage();
		}
	}

	public static void main(String[] args) {
		launch();
	}
}