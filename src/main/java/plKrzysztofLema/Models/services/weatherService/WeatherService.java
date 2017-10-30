package plKrzysztofLema.Models.services.weatherService;

import org.json.JSONObject;
import plKrzysztofLema.Models.Config;
import plKrzysztofLema.Models.Utils;

public class WeatherService {
    private static WeatherService ourInstance = new WeatherService();

    public static WeatherService getService() {
        return ourInstance;
    }

    private WeatherService() {
    }

    public void makeCall(String cityName) {
        readJsonData(Utils.makeHttpRequest(Config.APP_BASE_URL + cityName + "&appid=" + Config.APP_ID));


    }

    private void readJsonData(String json) {
        JSONObject root = new JSONObject(json);
        JSONObject main = root.getJSONObject("main");

        double temp = main.getDouble("temp");
        System.out.println("Temepratura to: " + temp);
    }

}
