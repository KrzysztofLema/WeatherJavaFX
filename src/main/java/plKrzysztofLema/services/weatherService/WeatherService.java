package plKrzysztofLema.services.weatherService;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

public class WeatherService {
    private static WeatherService ourInstance = new WeatherService();

    public static WeatherService getService() {
        return ourInstance;
    }

    private WeatherService() {
    }

    public void makeCall(String cityName){
        try {
            URLConnection urlConnection = (HttpURLConnection) new URL("http://api.openweathermap.org/data/2.5/weather?q="+cityName+"&appid=8dc379acad6ee71b6d730e293aa8efc7").openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
