package plKrzysztofLema.contollers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class ChartController implements Initializable {

    @FXML
    ListView<String> listOfCities;
    @FXML
    BarChart chartTemp;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
