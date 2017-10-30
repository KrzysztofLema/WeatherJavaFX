package plKrzysztofLema.Models.services.weatherService;

import org.json.JSONObject;
import plKrzysztofLema.Models.Config;
import plKrzysztofLema.Models.IWeatherObserver;
import plKrzysztofLema.Models.Utils;
import plKrzysztofLema.Models.WeatherInfo;

public class WeatherService {
    private static WeatherService ourInstance = new WeatherService();

    public static WeatherService getService() {
        return ourInstance;
    }

    private WeatherService() {
    }

    private IWeatherObserver observer;

    public void makeCall(String cityName) {

        readJsonData(Utils.makeHttpRequest(Config.APP_BASE_URL + cityName + "&appid=" + Config.APP_ID));
    }


    private void readJsonData(String json) {
        JSONObject root = new JSONObject(json);
        JSONObject main = root.getJSONObject("main");
        JSONObject wind = root.getJSONObject("wind");


        double visibility = root.getDouble("visibility");
        double temp = main.getDouble("temp");
        double pressure = main.getDouble("pressure");
        double windSpeed = wind.getDouble("speed");

        observer.onWeatherUpdate(new WeatherInfo(temp,pressure));

        System.out.println("Temepratura to: " + temp);
        System.out.println("Ciśneinie to: " + pressure);
        System.out.println("Widocznośc to: " + visibility);
        System.out.println("Prędkość wiatru wynosi: " + windSpeed);
    }

    public void registerObserver(IWeatherObserver observer) {
        this.observer = observer;
    }
}
