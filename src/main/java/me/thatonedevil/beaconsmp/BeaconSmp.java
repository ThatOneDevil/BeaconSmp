package me.thatonedevil.beaconsmp;

import me.thatonedevil.beaconsmp.Database.ConnectionListener;
import me.thatonedevil.beaconsmp.Database.Database;
import me.thatonedevil.beaconsmp.Database.PlayerManager;
import me.thatonedevil.beaconsmp.beacon.BeaconMenuClickEvent;
import me.thatonedevil.beaconsmp.beacon.BeaconMenuGui;
import me.thatonedevil.beaconsmp.economy.SellEvent;
import me.thatonedevil.beaconsmp.economy.SetValue;
import me.thatonedevil.beaconsmp.economy.SellGui;
import me.thatonedevil.beaconsmp.shop.ShopClickEvent;
import me.thatonedevil.beaconsmp.shop.ShopGui;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class BeaconSmp extends JavaPlugin {

    private Database database;
    private PlayerManager playerManager;

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        Database Database = new Database(this);

        database = new Database(this);
        playerManager = new PlayerManager();

        try {
            database.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("-----------------");
        if (database.isConnected()) {
            System.out.println("Database connection: " + database.isConnected() + " This is a good thing!");
        } else {
            System.out.println("Database connection: " + database.isConnected() + " This is a bad thing!");
        }
        System.out.println();
        System.out.println("-----------------");

        getCommand("setValue").setExecutor(new SetValue());
        getCommand("sell").setExecutor(new SellGui());
        getCommand("beaconmenu").setExecutor(new BeaconMenuGui());
        getCommand("shop").setExecutor(new ShopGui());


        Bukkit.getPluginManager().registerEvents(new SellEvent(), this);
        Bukkit.getPluginManager().registerEvents(new ShopClickEvent(), this);
        Bukkit.getPluginManager().registerEvents(new BeaconMenuClickEvent(), this);
        Bukkit.getPluginManager().registerEvents(new ConnectionListener(this), this);


    }

    @Override
    public void onDisable() {
        database.disconnect();
    }

    public static String format(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }
    public Database getDatabase() { return database; }
    public PlayerManager getPlayerManager() { return playerManager; }


}
