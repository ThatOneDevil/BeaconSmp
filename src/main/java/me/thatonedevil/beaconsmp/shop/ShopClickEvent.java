package me.thatonedevil.beaconsmp.shop;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ShopClickEvent implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){

        if (e.getInventory() != null && e.getCurrentItem() != null && e.getView().getTitle().contains("Beacon Shop")){
            e.setCancelled(true);
            getBuyPrice(e.getCurrentItem());
        }


    }
    public String getBuyPrice(ItemStack item) {
        if (!item.hasItemMeta()) return "";
        ItemMeta im = item.getItemMeta();

        if (!im.hasLore()) return "";

        for (String line : im.getLore()) {
            if (line.equalsIgnoreCase("Buy this for ")) {
                ChatColor.stripColor(line).replaceAll("[^0-9]", "");
                ArrayList<String> buyPrice = new ArrayList<>();
                buyPrice.add((line.split("for").toString()));
                Bukkit.broadcastMessage(buyPrice.get(1));
                Bukkit.broadcastMessage(buyPrice.get(2));

            }
        }
        return "";
    }
}
