package plKrzysztofLema.Models;

public class WeatherInfo {
    private double temp;
    private double pressure;
    private String cityName;


    public WeatherInfo(double temp, double pressure, String cityName) {
        this.temp = temp;
        this.pressure = pressure;
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }
}
