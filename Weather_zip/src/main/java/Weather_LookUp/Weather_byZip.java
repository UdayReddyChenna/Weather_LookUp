package Weather_LookUp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.Gson;

public class Weather_byZip {
	
	public static void main(String args[]) {
		
		System.out.println("Welcome to Weather Forecase, Please enter a Zip to get Tomorrows Temparature");
	try {
	BufferedReader reader =new BufferedReader(new InputStreamReader(System.in));
	String zip = reader.readLine();
	
	String city_look_up_URL = "https://www.zipcodeapi.com/rest/sZnRqsSoxsv9IKF4MSmp6SeaEtqgHnWNRWBfkWU79vtnMnT2BjuN8vr9iXMUJEke/info.json/";
	city_look_up_URL = city_look_up_URL+zip +"/radians";
	String api_restl = Weather_byZip.api_output(city_look_up_URL,City_Details.class);
	Gson g = new Gson();
	City_Details city_dtls = g.fromJson(api_restl, City_Details.class);
	System.out.println("Corresponding city for zip: "+zip +" is: "+city_dtls.getCity());
	String[] split_ctiy_name = city_dtls.getCity().split(" ");
	
	String weath_url = "https://weather.cit.api.here.com/weather/1.0/report.json?product=forecast_7days&name=";
	if(split_ctiy_name!=null && split_ctiy_name.length>1) {
		String city = "";
		for(int i=0;i<split_ctiy_name.length;i++) {
			city = city+split_ctiy_name[i] + "%20";
		}
		weath_url = weath_url + city +"&app_id=DemoAppId01082013GAL&app_code=AJKnXv84fjrb0KIHawS0Tg";
	}
	else {
		weath_url = weath_url + city_dtls.getCity() +"&app_id=DemoAppId01082013GAL&app_code=AJKnXv84fjrb0KIHawS0Tg";
	}
	String api_restl1 = Weather_byZip.api_output(weath_url,City_Details.class);
	Weather_city w = g.fromJson(api_restl1, Weather_city.class);
	System.out.println(java.time.LocalDate.now().plusDays(1));  
	
	if(w.getForecasts().getForecastLocation().getForecast()!=null && w.getForecasts().getForecastLocation().getForecast().size()>0) {
		for(forecast_weather fw :w.getForecasts().getForecastLocation().getForecast() ) {
			ZonedDateTime zonedDateTime = ZonedDateTime.parse(fw.getUtcTime());
			zonedDateTime.toLocalDateTime();
			if(java.time.LocalDate.now().plusDays(1).equals(zonedDateTime.toLocalDate())) {
				System.out.println("Weather at "+zonedDateTime.toLocalDateTime() + "---->" +fw.getTemperature() + "C");
			}
		}
	}
	}
	catch (Exception e) {
		e.printStackTrace();
	}
}

public static String api_output(String url,Class c) {
	String output = ""; 
	URL uri;
	try {
	uri = new URL(url);
	HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
	conn.setRequestMethod("GET");
	conn.setRequestProperty("Accept", "application/json");
		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
		output = br.readLine();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return output;
	}

}
