package plKrzysztofLema.contollers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import plKrzysztofLema.Models.services.weatherService.WeatherService;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @FXML
    Button buttonShowWeather;
    @FXML
    Label labelShowWeather;
    @FXML
    TextField textFieldCityName;

    private WeatherService weatherService = WeatherService.getService();

    public void initialize(URL location, ResourceBundle resources) {
        weatherService.makeCall("Cracow");

        buttonShowWeather.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (!textFieldCityName.getText().isEmpty()){
                weatherService.makeCall(textFieldCityName.getText());
                textFieldCityName.clear()
                ;}
            }
        });
    }
}
