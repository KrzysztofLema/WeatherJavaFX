package plKrzysztofLema.Models;

public class WeatherInfo {
    private double temp;
    private double pressure;

    public WeatherInfo(double temp, double pressure) {
        this.temp = temp;
        this.pressure = pressure;
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
