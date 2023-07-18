package com.example.group;

import java.io.Console;
import java.sql.*;

public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getConnection () {
        String databaseName = "pharmacy";
        String databaseUser = "root";
        String databasePassword = "oracle";
        String url = "jdbc:mysql://localhost:3306/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return databaseLink;
    }
}
