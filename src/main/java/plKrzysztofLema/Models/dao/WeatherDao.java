package plKrzysztofLema.Models.dao;

import plKrzysztofLema.Models.WeatherModel;

import java.util.List;

public interface WeatherDao {

    List<WeatherModel> showAll(String cityName);
    void addWeather(WeatherModel weatherModel);
    List<String> getAllCities();
}
