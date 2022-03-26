package me.thatonedevil.beaconsmp.shop;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class ShopClickEvent implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){

        Player player = (Player) e.getWhoClicked();

        if (e.getInventory() != null && e.getCurrentItem() != null && e.getView().getTitle().contains("Beacon Shop")){
            e.setCancelled(true);
            player.sendMessage((getBuyPrice(e.getCurrentItem()).toString()));
        }

    }
    public Double getBuyPrice(ItemStack item) {
        if (!item.hasItemMeta()) return 0d;
        ItemMeta im = item.getItemMeta();

        if (!im.hasLore()) return 0d;

        for (String line : im.getLore()) {
            String[] lore = ChatColor.stripColor(line).split("for");
            String buyPrice = lore[1];
            buyPrice.replace(" ", "");
            return Double.valueOf(buyPrice);
        }
        return 0d;
    }
}
