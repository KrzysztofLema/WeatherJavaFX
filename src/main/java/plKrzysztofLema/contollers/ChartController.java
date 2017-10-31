package plKrzysztofLema.contollers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;
import plKrzysztofLema.Models.WeatherModel;
import plKrzysztofLema.Models.dao.WeatherDao;
import plKrzysztofLema.Models.dao.daoImpl.WeatherDaoImpl;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class ChartController implements Initializable {

    @FXML
    ListView<String> listOfCities;
    @FXML
    BarChart chartTemp;

    ObservableList<String> cityObservableList;
    private WeatherDao weatherDao = new WeatherDaoImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cityObservableList = FXCollections.observableList(weatherDao.getAllCities());
        listOfCities.setItems(cityObservableList);
        listOfCities.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> generateChart(newValue));

    }

    private void generateChart(String cityName) {
        List<WeatherModel> weatherModelList = weatherDao.showAll(cityName);
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        series.setName(cityName);

        for (WeatherModel weatherModel : weatherModelList) {
            series.getData().add(new XYChart.Data<>(weatherModel.getDate().toString(), weatherModel.getTemp()));
        }
        chartTemp.getData().clear();
        chartTemp.getData().add(series);
    }
}
