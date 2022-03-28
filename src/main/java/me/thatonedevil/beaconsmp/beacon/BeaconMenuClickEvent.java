package me.thatonedevil.beaconsmp.beacon;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class BeaconMenuClickEvent implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked();
        if (e.getInventory() != null && e.getCurrentItem() != null && e.getView().getTitle().contains("Beacon Menu")) {
            if (e.getRawSlot() == 11){
                e.setCancelled(true);
                player.closeInventory();
                player.performCommand("shop");
            }


        }

    }
}
