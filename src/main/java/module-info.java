module me.arttostog.weather {
	requires javafx.controls;
	requires javafx.fxml;
	requires com.google.gson;
	requires java.net.http;
	requires okhttp3;

	opens me.arttostog.weather to javafx.fxml;
	exports me.arttostog.weather;

	opens me.arttostog.weather.user to javafx.fxml;
	exports me.arttostog.weather.user to com.google.gson, javafx.fxml;
	exports me.arttostog.weather.controller;
	opens me.arttostog.weather.controller to javafx.fxml;
	exports me.arttostog.weather.openweathermapapi.data_2_5 to com.google.gson;
	exports me.arttostog.weather.openweathermapapi.geo_1_0 to com.google.gson;
}