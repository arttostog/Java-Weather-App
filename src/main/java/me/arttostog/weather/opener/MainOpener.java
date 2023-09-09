package me.arttostog.weather.opener;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import me.arttostog.weather.WeatherApplication;
import me.arttostog.weather.controller.MainController;

import java.io.IOException;
import java.util.Objects;

public class MainOpener {
	private static final FXMLLoader fxmlLoaderMain = new FXMLLoader(WeatherApplication.class.getResource("main.fxml"));
	private static final Thread autoUpdate = new Thread(() -> {
		MainController wc = fxmlLoaderMain.getController();
		while (true) {
			try {
				Thread.sleep(60 * 1000);
				wc.update();
			} catch (InterruptedException e) {
				break;
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	});

	private static final Timeline timeLine = new Timeline(new KeyFrame(
			Duration.seconds(5),
			ActionEvent -> {
				try {
					Stage st = WeatherApplication.st;
					Scene sceneMain = new Scene(fxmlLoaderMain.load());

					st.setScene(sceneMain);
					st.show();
					st.setOnCloseRequest(windowEvent -> {
						autoUpdate.interrupt();
						Platform.exit();
					});

					autoUpdate.start();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
	));

	public static void showMainStage() throws IOException {
		Stage st = WeatherApplication.st;

		st.getIcons().add(new Image(Objects.requireNonNull(WeatherApplication.class.getResourceAsStream("icon.png"))));
		st.setResizable(false);
		st.setOnCloseRequest(windowEvent -> Platform.exit());
		st.setTitle("Погода");

		HelloOpener.showHello();

		timeLine.setCycleCount(1);
		timeLine.play();
	}
}
