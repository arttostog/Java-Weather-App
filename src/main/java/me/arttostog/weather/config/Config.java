package me.arttostog.weather.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.arttostog.weather.user.User;

import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;

public class Config {
	private static Config config;
	private final File file = new File("user.json");
	private final Gson gson = new Gson();

	public void saveUser(User user) throws IOException {
		FileWriter fw = new FileWriter(file);
		gson.toJson(user, fw);
		fw.flush();
	}

	public User getUser() throws IOException {
		return gson.fromJson(new FileReader(file), User.class);
	}

	public boolean isExist() {
		return file.exists();
	}

	public static Config getConfig() {
		return config == null ? (config = new Config()) : config;
	}
}
