package com.spark.ex.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    private String url;
    private String username;
    private String password;

    private static ConnectionUtil connectionManager;

    private ConnectionUtil(String url, String username, String password, Driver driver) throws SQLException {
        DriverManager.registerDriver(driver);
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static void createConnectionManager(String url, String username, String password, Driver driver) throws SQLException {
        if(connectionManager == null) {
            connectionManager = new ConnectionUtil(url, username, password, driver);
        }
    }

    public static ConnectionUtil getConnectionManager() {
        return connectionManager;
    }

    public Connection newConnection() throws SQLException {
        return DriverManager.getConnection(this.url, this.username, this.password);
    }
}
