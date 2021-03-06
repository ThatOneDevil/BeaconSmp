package me.thatonedevil.beaconsmp.beacon;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.gmail.filoghost.holographicdisplays.api.line.TextLine;
import me.thatonedevil.beaconsmp.BeaconSmp;
import me.thatonedevil.beaconsmp.db.CustomPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.sql.SQLException;
import java.util.*;

import static me.thatonedevil.beaconsmp.BeaconSmp.format;

public class BeaconEvents implements Listener {

    private BeaconSmp main;
    private Hologram holo;

    public BeaconEvents(BeaconSmp main) {
        this.main = main;
    }

    @EventHandler
    public void onPlaceEvent(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        Block block = e.getBlock();
        if (player.getItemInHand().getType() == Material.BEACON) {//&& player.getItemInHand().getItemMeta().getLore().contains(format(ChatColor.AQUA + player.getName() + "&b's Beacon"))) {

            try {
                CustomPlayer playerData = new CustomPlayer(main, player.getUniqueId());
                playerData.setBeaconLoc("Placed");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            Location loc = block.getLocation();
            loc.add(0, 2,0);

            holo = HologramsAPI.createHologram(main, loc);
            holo.setAllowPlaceholders(true);
            TextLine textLine1 = holo.appendTextLine(format("&3" + player.getName() + "'s &bBeacon"));
            TextLine textLine2 = holo.appendTextLine(format("&3Your stars are {stars}&c⭐"));
            TextLine textLine3 = holo.appendTextLine(format("&3Gang: &cCOMING SOON!"));
            TextLine textLine4 = holo.appendTextLine(format("&3Owner: &cCOMING SOON!"));
            TextLine textLine5 = holo.appendTextLine(format("&3Member count: &cCOMING SOON!"));
            player.sendMessage(format("&bPlaced player beacon, at" + block.getLocation()));

        }
    }
    @EventHandler
    public void onBreakEvent(BlockBreakEvent e) {
        Player player = e.getPlayer();
        Block block = e.getBlock();
        try {
            CustomPlayer playerData = new CustomPlayer(main, player.getUniqueId());
            if (block.getType().equals(Material.BEACON) && playerData.getBeaconLoc().equals("Placed")) {
                playerData.setBeaconLoc("Not placed");
                holo.delete();
                player.sendMessage(format("&bPicked up beacon!"));
                player.getInventory().addItem(beacon(player));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private ItemStack beacon(Player player){
        ItemStack beacon = new ItemStack(Material.BEACON);
        ItemMeta data = beacon.getItemMeta();
        data.setDisplayName("Beacon");
        data.setLore(Arrays.asList(format(ChatColor.AQUA + player.getName() + "'s Beacon")));
        beacon.setItemMeta(data);
        return beacon;
    }
}
