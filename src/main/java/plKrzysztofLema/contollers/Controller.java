package plKrzysztofLema.contollers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import plKrzysztofLema.Models.IWeatherObserver;
import plKrzysztofLema.Models.WeatherInfo;
import plKrzysztofLema.Models.services.weatherService.WeatherService;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable, IWeatherObserver {


    @FXML
    Button buttonShowWeather;
    @FXML
    Label labelShowWeather;
    @FXML
    TextField textFieldCityName;

    private WeatherService weatherService = WeatherService.getService();

    public void initialize(URL location, ResourceBundle resources) {
        weatherService.registerObserver(this);

        buttonShowWeather.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (!textFieldCityName.getText().isEmpty()){
                weatherService.makeCall(textFieldCityName.getText());
                textFieldCityName.clear()
                ;}
            }
        });
        buttonShowWeather.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode()== KeyCode.ENTER){
                    if (!textFieldCityName.getText().isEmpty()){
                    weatherService.makeCall(textFieldCityName.getText());
                    textFieldCityName.clear();}
                }
            }
        });
        textFieldCityName.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode()==KeyCode.ENTER){
                    weatherService.makeCall(textFieldCityName.getText());
                    textFieldCityName.clear();
                }
            }
        });
    }

    @Override
    public void onWeatherUpdate(WeatherInfo info) {
    labelShowWeather.setText("Temperatura: "+info.getTemp()+ "Cisnienie: " + info.getPressure());
    }
}
