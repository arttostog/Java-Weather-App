package me.arttostog.weather.user;

public class User {
	private static User user;
	public static final int MAX_NAME_LENGTH = 25;
	public final String name;
	public final String city;
	public final String apiKey;

	public User(String name, String city, String APIKey) {
		this.name = name;
		this.city = city;
		this.apiKey = APIKey;
	}

	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		User.user = user;
	}
}
