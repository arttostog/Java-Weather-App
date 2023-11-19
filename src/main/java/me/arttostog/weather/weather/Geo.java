package me.arttostog.weather.weather;

import me.arttostog.weather.openweathermapapi.geo_1_0.GeoAPIResponse;
import me.arttostog.weather.request.GeoRequest;

import java.io.IOException;

public class Geo {
	private final double Lat;
	private final double Lon;

	public Geo() throws IOException {
		GeoAPIResponse response = new GeoRequest().getResponse();
		this.Lat = response.lat;
		this.Lon = response.lon;
	}

	public double getLon() {
		return Lon;
	}

	public double getLat() {
		return Lat;
	}
}
