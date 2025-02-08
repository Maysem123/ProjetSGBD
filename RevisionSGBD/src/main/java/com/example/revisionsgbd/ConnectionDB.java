package com.example.revisionsgbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/revisiondb";
    private static final String USER = "root";
    private static final String PASSWORD = "";


    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        return conn;
    }

}
