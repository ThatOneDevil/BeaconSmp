package me.thatonedevil.beaconsmp;

import me.thatonedevil.beaconsmp.economy.SellEvent;
import me.thatonedevil.beaconsmp.economy.SetValue;
import me.thatonedevil.beaconsmp.economy.ValueGui;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class BeaconSmp extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("setValue").setExecutor(new SetValue());
        getCommand("sell").setExecutor(new ValueGui());

        Bukkit.getPluginManager().registerEvents(new SellEvent(), this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static String format(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }
}
