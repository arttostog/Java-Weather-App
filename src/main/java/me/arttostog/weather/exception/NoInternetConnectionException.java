package me.arttostog.weather.exception;

public class NoInternetConnectionException extends Exception {
	public NoInternetConnectionException(String ErrorMessage) {
		super(ErrorMessage);
	}
}
