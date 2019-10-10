package Weather_LookUp;

import java.util.ArrayList;

public class ForecastLocation {
	
	 private String country;
	 private String state;
	 private String city;
	 private float latitude;
	 private float longitude;
	 private float distance;
	 private float timezone;
	 
	 ArrayList < forecast_weather > forecast = new ArrayList < forecast_weather > ();

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public float getTimezone() {
		return timezone;
	}

	public void setTimezone(float timezone) {
		this.timezone = timezone;
	}

	public ArrayList<forecast_weather> getForecast() {
		return forecast;
	}

	public void setForecast(ArrayList<forecast_weather> forecast) {
		this.forecast = forecast;
	}

}
