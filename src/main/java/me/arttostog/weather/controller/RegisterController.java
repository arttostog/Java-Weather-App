package me.arttostog.weather.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import me.arttostog.weather.request.ValidateDataRequest;
import me.arttostog.weather.timeline.ButtonAnimationTimeLine;
import me.arttostog.weather.config.Config;
import me.arttostog.weather.opener.RegisterOpener;
import me.arttostog.weather.user.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
	@FXML
	private Text errorText;
	@FXML
	private TextField nameTextField;
	@FXML
	private TextField cityTextField;
	@FXML
	private TextField tokenTextField;
	@FXML
	public Button submitButton;
	@FXML
	private void submit() {
		new ButtonAnimationTimeLine(submitButton).startTimeLine();

		try {
			if (!checkFields()) return;
			Config.getConfig().saveUser(new User(nameTextField.getText(), cityTextField.getText(), tokenTextField.getText()));
			RegisterOpener.getInstance().setUser();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		RegisterOpener.getInstance().hideRegisterStage();
	}

	private boolean checkFields() throws IOException {
		if (nameTextField.getText().isEmpty() || cityTextField.getText().isEmpty() || tokenTextField.getText().isEmpty()
				|| nameTextField.getText().length() > User.MAX_NAME_LENGTH || new ValidateDataRequest(cityTextField.getText(), tokenTextField.getText()).getResponse()) {
			errorText.setVisible(true);
			return false;
		}
		return true;
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		nameTextField.setPromptText("Максимум " + User.MAX_NAME_LENGTH + " символов");
		errorText.setVisible(false);
	}
}
