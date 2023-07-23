package me.arttostog.weather.user;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import me.arttostog.weather.WeatherApplication;
import me.arttostog.weather.utills.Background;
import me.arttostog.weather.utills.LabelByTime;
import me.arttostog.weather.weather.Weather;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
	@FXML
	private VBox MainBox;
	@FXML
	private Label Hello;
	@FXML
	private Label User;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		MainBox.setStyle(Background.GetBackground(Weather.WeatherMain));
		Hello.setText(LabelByTime.GetLabel());

		Font DefaultFontSize = Font.font(24);
		int NameLength = WeatherApplication.user.Name.length();
		if (NameLength > 17) {
			User.setFont(Font.font(DefaultFontSize.getSize() - (NameLength - 17)));
		}

		User.setText(WeatherApplication.user.Name + "!");
	}
}
