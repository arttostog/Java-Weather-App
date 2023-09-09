package me.arttostog.weather.opener;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.arttostog.weather.WeatherApplication;

import java.io.IOException;

public class HelloOpener {
	public static void showHello() throws IOException {
		Scene sceneHello = new Scene(new FXMLLoader(WeatherApplication.class.getResource("hello.fxml")).load());
		Stage st = WeatherApplication.st;
		st.setScene(sceneHello);
		st.show();
	}
}
