package me.arttostog.weather.user;

public class User {
	public final String Name;
	public final String City;
	public final String APIKey;

	public User(String name, String city, String APIKey) {
		Name = name;
		City = city;
		this.APIKey = APIKey;
	}
}
