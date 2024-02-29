package com.cowards.onlyarts.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class DBContext {

    private static final String DB_NAME = "OnlyArts";
    private static final String DB_USERNAME = "sa";
    private static final String DB_PASSWORD = "12345";

    public static Connection getConnection()
            throws SQLException {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;DatabaseName=" + DB_NAME + ";encrypt=true;trustServerCertificate=true;";
            conn = DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, "Exception found in DBContext", ex);
        }
        return conn;
    }

    public static void closePreparedStatement(PreparedStatement ptm) {
        if (ptm != null) {
            try {
                if(!ptm.isClosed())ptm.close();
            } catch (SQLException e) {
                Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, "Exception found in DBContext", e);
            }
        }
    }
    
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                if(!conn.isClosed())conn.close();
            } catch (SQLException e) {
                Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, "Exception found in DBContext", e);
            }
        }
    }

    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                if(!rs.isClosed())rs.close();
            } catch (SQLException e) {
                Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, "Exception found in DBContext", e);
            }
        }
    }
}
