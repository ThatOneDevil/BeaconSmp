package me.thatonedevil.beaconsmp.Database;

import me.thatonedevil.beaconsmp.BeaconSmp;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.SQLException;

public class ConnectionListener implements Listener{

    private BeaconSmp main;

    public ConnectionListener(BeaconSmp main) {
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        try {
            CustomPlayer playerData = new CustomPlayer(main, e.getPlayer().getUniqueId());
            main.getPlayerManager().addCustomPlayer(e.getPlayer().getUniqueId(), playerData);
        } catch (SQLException ex) {
            ex.printStackTrace();
            e.getPlayer().kickPlayer(ChatColor.RED + "Your data could not be loaded \n Please contact an admin ASAP");
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        main.getPlayerManager().removeCustomPlayer(e.getPlayer().getUniqueId());
    }

}
