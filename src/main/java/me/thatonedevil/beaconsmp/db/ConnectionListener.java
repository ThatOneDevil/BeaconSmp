package me.thatonedevil.beaconsmp.db;

import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import me.thatonedevil.beaconsmp.BeaconSmp;
import me.thatonedevil.beaconsmp.beacon.HoloPlaceHolder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class ConnectionListener implements Listener{

    private final BeaconSmp main;
    private HashMap<UUID, Double> playerStars;

    public ConnectionListener(BeaconSmp main) {
        this.main = main;
    }
    private CustomPlayer playerData;

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        try {
            playerData = new CustomPlayer(main, e.getPlayer().getUniqueId());
            main.getPlayerManager().addCustomPlayer(e.getPlayer().getUniqueId(), playerData);
        } catch (SQLException ex) {
            ex.printStackTrace();
            e.getPlayer().kickPlayer(ChatColor.RED + "Your data could not be loaded \n Please contact an admin ASAP");
        }

        playerStars = new HashMap<>();
        playerStars.put(e.getPlayer().getUniqueId(), playerData.getStars());
        e.getPlayer().sendMessage(String.valueOf(playerStars.get(e.getPlayer().getUniqueId())));

        for (Player player : Bukkit.getOnlinePlayers()) {
            HologramsAPI.registerPlaceholder(main, "{stars}", 20,
                    new HoloPlaceHolder(playerStars.get(player.getUniqueId())));
            player.sendMessage("Refreshed");
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        main.getPlayerManager().removeCustomPlayer(e.getPlayer().getUniqueId());
        playerStars.remove(e.getPlayer().getUniqueId());
    }

}
