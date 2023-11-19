package me.arttostog.weather.controller;

import com.google.gson.JsonParser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import me.arttostog.weather.request.TestRequest;
import me.arttostog.weather.timeline.ButtonAnimationTimeLine;
import me.arttostog.weather.config.Config;
import me.arttostog.weather.opener.RegisterOpener;
import me.arttostog.weather.request.Request;
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
		new ButtonAnimationTimeLine(button).startTimeLine();

		try {
			if (!checkFields()) return;
			Config.getConfig().saveUser(new User(name.getText(), city.getText(), token.getText()));
			RegisterOpener.getInstance().setUser();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		RegisterOpener.getInstance().hideRegisterStage();
	}

	private boolean checkFields() throws IOException {
		if (name.getText().isEmpty() || city.getText().isEmpty() || token.getText().isEmpty()
				|| name.getText().length() > User.MAX_NAME_LENGTH || new TestRequest(city.getText(), token.getText()).getResponse()) {
			error.setVisible(true);
			return false;
		}
		return true;
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		name.setPromptText("Максимум " + User.MAX_NAME_LENGTH + " символов");
		error.setVisible(false);
	}
}
