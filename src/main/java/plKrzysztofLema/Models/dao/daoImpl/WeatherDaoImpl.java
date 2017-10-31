package plKrzysztofLema.Models.dao.daoImpl;

import plKrzysztofLema.Models.MySqlConnector;
import plKrzysztofLema.Models.dao.WeatherDao;
import plKrzysztofLema.Models.WeatherModel;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WeatherDaoImpl implements WeatherDao {
    MySqlConnector connector = MySqlConnector.getInstace();

    @Override
    public List<WeatherModel> showAll(String cityName) {
        List<WeatherModel> weatherModels = new ArrayList<>();
        try {
            PreparedStatement statement=connector.getConnection().prepareStatement("SELECT * FROM weather WHERE cityName=?");
            statement.setString(1,cityName);
            ResultSet resultSet  = statement.executeQuery();
            while (resultSet.next()){
                weatherModels.add(new WeatherModel(resultSet.getString("cityName"),resultSet.getFloat("temp"),resultSet.getDate("date")));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return weatherModels;
    }

    @Override
    public void addWeather(WeatherModel weatherModel) {
        try {
            PreparedStatement statement = connector.getConnection().prepareStatement("INSERT INTO weather VALUES (?,?,?,?)");
            statement.setInt(1,0);
            statement.setString(2,weatherModel.getCityName());
            statement.setFloat(3,weatherModel.getTemp());
            statement.setDate(4, null);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getAllCities() {
        List<String> cityNames = new ArrayList<>();
        try {
            PreparedStatement statement = connector.getConnection().prepareStatement("SELECT cityName FROM weather");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                cityNames.add(resultSet.getString("cityName"));
            }
            statement.close();
            return cityNames;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
