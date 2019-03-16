package plKrzysztofLema.Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnector {
    private static final String SQL_LINK = "";
    private static final String SQL_USER = "";
    private static final String SQL_PASSWORD = "";
    private static final String SQL_CLASS = "com.mysql.cj.jdbc.Driver";


    private static MySqlConnector connector = new MySqlConnector();

    public static MySqlConnector getInstace() {
        return connector;
    }


    private MySqlConnector() {
        connect();
    }

    private Connection connection;

    private void connect() {
        try {
            Class.forName(SQL_CLASS);

            connection =
                    DriverManager.getConnection(SQL_LINK, SQL_USER, SQL_PASSWORD);

            System.out.println("Połączono");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
