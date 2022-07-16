package me.thatonedevil.beaconsmp.listeners;

import me.thatonedevil.beaconsmp.BeaconSmp;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static me.thatonedevil.beaconsmp.BeaconSmp.api;

public class OnJoin implements Listener {

    private BeaconSmp main;

    public OnJoin(BeaconSmp main){
        this.main = main;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();

        YamlConfiguration config = api.getOrCreate(player);

        try {
            if (!player.hasPlayedBefore() || !config.get("Exists").equals(true)) {
                api.create(player);
                YamlConfiguration configNew = api.getOrCreate(player);
                configNew.set("Name", player.getName());
                configNew.set("Stars", 0);
                configNew.set("BeaconLocation", "N/A");
                api.save(player, configNew);

            }
        } catch (NullPointerException ex) {
            api.create(player);
            YamlConfiguration configNew = api.getOrCreate(player);
            configNew.set("Name", player.getName());
            configNew.set("Stars", "");
            configNew.set("BeaconLocation", "");
            api.save(player, configNew);
        }


    }
}
