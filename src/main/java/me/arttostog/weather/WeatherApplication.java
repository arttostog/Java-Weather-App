package me.arttostog.weather;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import me.arttostog.weather.config.Config;
import me.arttostog.weather.exception.NoInternetConnectionException;
import me.arttostog.weather.request.RequestCreator;
import me.arttostog.weather.user.Register;
import me.arttostog.weather.user.User;
import me.arttostog.weather.weather.Weather;

import java.io.IOException;
import java.util.Objects;

public class WeatherApplication extends Application {
	public static User user;

	private static final FXMLLoader fxmlLoaderMain = new FXMLLoader(WeatherApplication.class.getResource("main.fxml"));
	private static Stage st;

	@Override
	public void start(Stage stage) throws IOException, NoInternetConnectionException {
		st = stage;

		boolean connection = CheckInternetConnection();
		if (!connection) {
			throw new NoInternetConnectionException("Нет интернет-соединения!");
		}

		Register.Login();

		if (Config.isExist()) {
			Weather.Update();
			ShowMainStage();
		}
	}

	private static boolean CheckInternetConnection() throws IOException {
		return RequestCreator.CreateTest("https://www.google.com/") == 200;
	}

	private static void ShowMainStage() throws IOException {
		st.getIcons().add(new Image(Objects.requireNonNull(WeatherApplication.class.getResourceAsStream("icon.png"))));
		st.setResizable(false);
		st.setOnCloseRequest(windowEvent -> Platform.exit());
		st.setTitle("Погода");

		ShowHello();

		tl.setCycleCount(1);
		tl.play();
	}

	private static void ShowHello() throws IOException {
		FXMLLoader fxmlLoaderHello = new FXMLLoader(WeatherApplication.class.getResource("hello.fxml"));
		Scene sceneHello = new Scene(fxmlLoaderHello.load());
		st.setScene(sceneHello);
		st.show();
	}

	private static final Timeline tl = new Timeline(new KeyFrame(
			Duration.seconds(5),
			ActionEvent -> {
				Scene sceneMain;
				try {
					sceneMain = new Scene(fxmlLoaderMain.load());
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
				st.setOnCloseRequest(windowEvent -> {
					WeatherApplication.Update.interrupt();
					Platform.exit();
				});
				st.setScene(sceneMain);
				st.show();
				WeatherApplication.Update.start();
			}
	));

	private static final Thread Update = new Thread(() -> {
		WeatherController wc = fxmlLoaderMain.getController();
		while (true) {
			try {
				Thread.sleep(60 * 1000);
				Weather.Update();
			} catch (IOException e) {
				throw new RuntimeException(e);
			} catch (InterruptedException e) {
				break;
			}
			wc.Update();
		}
	});

	public static void main(String[] args) {
		launch();
	}
}