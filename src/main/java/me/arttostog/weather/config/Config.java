package me.arttostog.weather.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.arttostog.weather.user.User;

import java.io.*;

public class Config {
	private final static File File = new File("user.json");
	private final static Gson gson = new GsonBuilder().setPrettyPrinting().setLenient().create();
	public static void SaveUser(User user) throws IOException {
		FileWriter fw = new FileWriter(File);
		gson.toJson(user, fw);
		fw.flush();
	}

	public static User GetUser() throws IOException {
		return gson.fromJson(new FileReader(File), User.class);
	}

	public static boolean isExist() {
		return File.exists();
	}
}
