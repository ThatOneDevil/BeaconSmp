package me.thatonedevil.beaconsmp.Config;

import me.thatonedevil.beaconsmp.BeaconSmp;

public class ConfigManager {

    private BeaconSmp main;

    public ConfigManager(BeaconSmp main) {
        this.main = main;
    }

    public String getHost() {
        return main.getConfig().getString("Host");
    }

    public Integer getPort() {
        return main.getConfig().getInt("Port");
    }

    public String getDatabase() {
        return main.getConfig().getString("Database");
    }

    public String getUsername() {
        return main.getConfig().getString("Username");
    }

    public String getPassword() {
        return main.getConfig().getString("Password");
    }

}
