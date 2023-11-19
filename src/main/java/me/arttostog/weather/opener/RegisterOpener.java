package me.arttostog.weather.opener;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import me.arttostog.weather.WeatherApplication;
import me.arttostog.weather.config.Config;
import me.arttostog.weather.stage.RegisterStageSettings;
import me.arttostog.weather.stage.StageSettings;
import me.arttostog.weather.user.User;

import java.io.IOException;

public class RegisterOpener extends Opener {
	private static RegisterOpener opener;
	private double xOffset = 0;
	private double yOffset = 0;

	RegisterOpener(Stage stage) {
		super(stage, new RegisterStageSettings(stage), true,
				new FXMLLoader(WeatherApplication.class.getResource("register.fxml")));
	}

	@Override
	public void open() throws IOException {
		if (setUser()) return;

		StageSettings settings = new RegisterStageSettings(stage);
		settings.setToDefault();
		settings.set();

		stage.setScene(getScene());
		stage.showAndWait();
	}

	public boolean setUser() throws IOException {
		Config config = Config.getConfig();
		if (config.isExist()) {
			User.setUser(config.getUser());
			return true;
		}
		return false;
	}

	@Override
	Scene getScene() throws IOException {
		Scene scene = new Scene(loader.load(), Color.TRANSPARENT);

		scene.setOnMousePressed(event -> {
			xOffset = stage.getX() - event.getScreenX();
			yOffset = stage.getY() - event.getScreenY();
		});
		scene.setOnMouseDragged(event -> {
			stage.setX(event.getScreenX() + xOffset);
			stage.setY(event.getScreenY() + yOffset);
		});

		return scene;
	}

	public void hideRegisterStage() {
		stage.hide();
	}

	public static RegisterOpener getInstance() {
		return opener == null ? (opener = new RegisterOpener(new Stage())) : opener;
	}
}
