package plKrzysztofLema.contollers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import javafx.stage.Stage;
import plKrzysztofLema.Models.IWeatherObserver;
import plKrzysztofLema.Models.WeatherInfo;
import plKrzysztofLema.Models.WeatherModel;
import plKrzysztofLema.Models.dao.WeatherDao;
import plKrzysztofLema.Models.dao.daoImpl.WeatherDaoImpl;
import plKrzysztofLema.Models.services.weatherService.WeatherService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable, IWeatherObserver {


    @FXML
    Button buttonShowWeather, buttonCharts;
    @FXML
    Label labelShowWeather;
    @FXML
    TextField textFieldCityName;
    @FXML
    ProgressIndicator progressIndicator;

    WeatherDao weatherDao=new WeatherDaoImpl();

    private WeatherService weatherService = WeatherService.getService();

    public void initialize(URL location, ResourceBundle resources) {
        weatherService.registerObserver(this);
        progressIndicator.setVisible(false);
        buttonShowWeather.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                progressIndicator.setVisible(true);

                if (!textFieldCityName.getText().isEmpty()) {
                    weatherService.makeCall(textFieldCityName.getText());
                    textFieldCityName.clear()
                    ;
                }
            }
        });
        buttonShowWeather.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    progressIndicator.setVisible(true);

                    if (!textFieldCityName.getText().isEmpty()) {
                        weatherService.makeCall(textFieldCityName.getText());
                        textFieldCityName.clear();
                    }
                }
            }
        });
        textFieldCityName.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    weatherService.makeCall(textFieldCityName.getText());
                    textFieldCityName.clear();
                }
            }
        });
        buttonCharts.setOnMouseClicked(e -> goToCharts());
    }

    private void goToCharts() {
        Stage stage = (Stage) buttonCharts.getScene().getWindow();

        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("chartView.fxml"));
            stage.setScene(new Scene(root, 600, 400));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onWeatherUpdate(WeatherInfo info) {
        progressIndicator.setVisible(false);
        labelShowWeather.setText("Temperatura: " + info.getTemp() + " | Cisnienie: " + info.getPressure());
        labelShowWeather.setVisible(true);
        weatherDao.addWeather(new WeatherModel(info));
    }
}
