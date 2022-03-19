package me.thatonedevil.beaconsmp.economy;


import me.thatonedevil.beaconsmp.BeaconSmp;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Beacon;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.Listener;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;


import java.util.ArrayList;

import static me.thatonedevil.beaconsmp.BeaconSmp.format;
import static me.thatonedevil.beaconsmp.economy.NbtData.getData;

public class SellEvent implements Listener{

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e){
        Player player = (Player) e.getPlayer();
        if (e.getInventory() != null && e.getView().getTitle().contains("Close inventory to sell!")){

            if (e.getInventory().getContents() != null) {

                ArrayList<Double> totalPrice = new ArrayList<>();
                for (ItemStack item : player.getOpenInventory().getTopInventory().getContents()) {
                    if (item != null) {
                        totalPrice.add(getData(item, "Value") * item.getAmount());
                    }

                }
                for (Double d : totalPrice) {
                    player.sendMessage(d.toString());
                }
            }else{
                player.sendMessage(ChatColor.RED + "&cThere is no item in the inventory");
            }

            //player.sendMessage(format("&bSold all items for &3" + totalPrice));
        }
    }
    @EventHandler
    public void inventoryClickEvent(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();
        if (e.getInventory() != null && e.getView().getTitle().contains("Close inventory to sell!")) {
            if (item != null) {
                NamespacedKey key = new NamespacedKey(BeaconSmp.getPlugin(BeaconSmp.class), "value");
                ItemMeta itemMeta = item.getItemMeta();
                PersistentDataContainer container = itemMeta.getPersistentDataContainer();

                if (!container.has(key, PersistentDataType.DOUBLE)) {
                    e.setCancelled(true);
                    player.sendMessage(format("&cSorry, but this item does not have a value!"));
                }
            }
        }

    }

}
