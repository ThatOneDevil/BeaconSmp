package me.thatonedevil.beaconsmp;

import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import me.thatonedevil.beaconsmp.beacon.*;
import me.thatonedevil.beaconsmp.config.PlayerDataAPI;
import me.thatonedevil.beaconsmp.economy.SellEvent;
import me.thatonedevil.beaconsmp.economy.SetValue;
import me.thatonedevil.beaconsmp.economy.SellGui;
import me.thatonedevil.beaconsmp.shop.ShopClickEvent;
import me.thatonedevil.beaconsmp.shop.ShopGui;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;


public final class BeaconSmp extends JavaPlugin {

    public static PlayerDataAPI api;

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getCommand("setValue").setExecutor(new SetValue());
        getCommand("sell").setExecutor(new SellGui());
        getCommand("beaconmenu").setExecutor(new BeaconMenuGui());
        getCommand("shop").setExecutor(new ShopGui());
        getCommand("deleteAllHolo").setExecutor(new DeleteHolograms(this));


        Bukkit.getPluginManager().registerEvents(new SellEvent(), this);
        Bukkit.getPluginManager().registerEvents(new ShopClickEvent(), this);
        Bukkit.getPluginManager().registerEvents(new BeaconMenuClickEvent(), this);
        Bukkit.getPluginManager().registerEvents(new BeaconEvents(this), this);

        api = new PlayerDataAPI(this);



    }

    @Override
    public void onDisable() {

    }

    public static String format(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }


}
