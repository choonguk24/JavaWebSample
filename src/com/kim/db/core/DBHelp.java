package com.kim.db.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelp {
    private static String url = "jdbc:mysql://localhost:3306/mydb";
    private static String username = "root";
    private static String password = "root";

    static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }
}
