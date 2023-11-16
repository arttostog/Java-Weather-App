package me.arttostog.weather.controller;

import com.google.gson.JsonParser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import me.arttostog.weather.animation.RegisterControllerButtonGuiAnimation;
import me.arttostog.weather.config.Config;
import me.arttostog.weather.opener.RegisterOpener;
import me.arttostog.weather.request.ApiRequestCreator;
import me.arttostog.weather.user.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
	@FXML
	private Text error;
	@FXML
	private TextField name;
	@FXML
	private TextField city;
	@FXML
	private TextField token;
	@FXML
	public Button button;
	@FXML
	private void submit() {
		new RegisterControllerButtonGuiAnimation(button).startAnimation();

		try {
			if (!checkFields()) return;
			Config.getConfig().saveUser(new User(name.getText(), city.getText(), token.getText()));
			RegisterOpener.getOpener().setUser();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		RegisterOpener.getOpener().hideRegisterStage();
	}

	private boolean checkFields() throws IOException {
		if (name.getText().isEmpty() || city.getText().isEmpty() || token.getText().isEmpty()
				|| name.getText().length() > User.MAX_NAME_LENGTH || test(token.getText(), city.getText())) {
			error.setVisible(true);
			return false;
		}
		return true;
	}

	private boolean test(String Token, String City) throws IOException {
		return JsonParser.parseString(new ApiRequestCreator(
				"https://api.openweathermap.org/geo/1.0/direct?q=" + City + "&limit=1&appid=" + Token
		).create()).getAsJsonObject().get("cod") != null;
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		name.setPromptText("Максимум " + User.MAX_NAME_LENGTH + " символов");
		error.setVisible(false);
	}
}
