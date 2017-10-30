package plKrzysztofLema.Models.services.weatherService;

import plKrzysztofLema.Models.Config;
import plKrzysztofLema.Models.Utils;

public class WeatherService {
    private static WeatherService ourInstance = new WeatherService();

    public static WeatherService getService() {
        return ourInstance;
    }

    private WeatherService() {
    }

    public void makeCall(String cityName){
        System.out.println(Utils.makeHttpRequest(Config.APP_BASE_URL+cityName+"&appid="+Config.APP_ID));

    }

}
