package plKrzysztofLema.Models;
import java.sql.Date;

public class WeatherModel {
    private String cityName;
    private float temp;
    private Date date;

    public WeatherModel(String cityName, float temp, Date date) {
        this.cityName = cityName;
        this.temp = temp;
        this.date = date;
    }

    public WeatherModel(WeatherInfo info){
        this.cityName = info.getCityName();
        this.temp = (float) info.getTemp(); //todo rozbieżność pol , do poprawy i refactoru
        this.date = new Date(0);

    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
