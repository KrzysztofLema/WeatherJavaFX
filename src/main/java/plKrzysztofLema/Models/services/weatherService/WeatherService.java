package plKrzysztofLema.Models.services.weatherService;

import javafx.application.Platform;
import org.json.JSONObject;
import plKrzysztofLema.Models.Config;
import plKrzysztofLema.Models.IWeatherObserver;
import plKrzysztofLema.Models.Utils;
import plKrzysztofLema.Models.WeatherInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WeatherService {
    private static WeatherService ourInstance = new WeatherService();

    public static WeatherService getService() {
        return ourInstance;
    }

    private ExecutorService executorService;

    private WeatherService() {
        executorService = Executors.newSingleThreadExecutor();
    }

    private List<IWeatherObserver> observer = new ArrayList<IWeatherObserver>();

    public void makeCall(String cityName) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                readJsonData(Utils.makeHttpRequest(Config.APP_BASE_URL + cityName + "&appid=" + Config.APP_ID),cityName);
            }
        };
        executorService.execute(runnable);
    }


    private void readJsonData(String json, String cityName) {
        JSONObject root = new JSONObject(json);
        JSONObject main = root.getJSONObject("main");
        JSONObject wind = root.getJSONObject("wind");


        double visibility = root.getDouble("visibility");
        final double temp = main.getDouble("temp");
        final double pressure = main.getDouble("pressure");
        double windSpeed = wind.getDouble("speed");

        observer.forEach(s->Platform.runLater(() -> s.onWeatherUpdate(new WeatherInfo(temp,pressure,cityName))));

        System.out.println("Temepratura to: " + temp);
        System.out.println("Ciśneinie to: " + pressure);
        System.out.println("Widocznośc to: " + visibility);
        System.out.println("Prędkość wiatru wynosi: " + windSpeed);
    }

    public void registerObserver(IWeatherObserver observer) {
        this.observer.add(observer);
    }
}
