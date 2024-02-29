package com.cowards.onlyarts.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class DBContext {

    private static final String DB_NAME = "OnlyArts";
    private static final String DB_USERNAME = "sa";
    private static final String DB_PASSWORD = "12345";

    private static Connection instance;

    private DBContext() {
    }

    private static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;DatabaseName=" + DB_NAME + ";encrypt=true;trustServerCertificate=true;";
            conn = DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, "Exception found on getConnection() method!", e);
        }
        return conn;
    }

    public static Connection getInstance() {
        if (instance == null) {
            instance = getConnection();
        }
        return instance;
    }
}
