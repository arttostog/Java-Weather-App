package me.arttostog.weather.opener;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class Opener {
	final Stage stage;
	final FXMLLoader loader;

	Opener(Stage stage, FXMLLoader loader) {
		this.stage = stage;
		this.loader = loader;
	}

	public abstract void open() throws IOException;

	abstract Scene getScene() throws IOException;
}