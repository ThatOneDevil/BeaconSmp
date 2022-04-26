package me.thatonedevil.beaconsmp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import me.thatonedevil.beaconsmp.BeaconSmp;
import me.thatonedevil.beaconsmp.dbConfig.ConfigManager;


public class Database {

    final ConfigManager configManager;
    final String host, database, username, password;
    final int port;
    public Database(BeaconSmp main) {
        configManager = new ConfigManager(main);
        host = configManager.getHost();
        port = configManager.getPort();
        database = configManager.getDatabase();
        username = configManager.getUsername();
        password = configManager.getPassword();
    }

    private Connection connection;

    public void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false",
                username,
                password);
    }

    public void disconnect(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() { return connection; }

    public boolean isConnected() {
        return connection != null;
    }

}
