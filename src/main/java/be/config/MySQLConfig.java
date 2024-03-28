package be.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConfig {
    public static Connection getConnection() {


        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/accountdb",
                    "chef",
                    "2024");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}








