package me.thatonedevil.beaconsmp.economy;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import static me.thatonedevil.beaconsmp.BeaconSmp.format;

public class SellGui implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player)sender;
            Inventory inv = Bukkit.createInventory(null, 54, format("&bClose inventory to sell"));

            player.openInventory(inv);
        }
        return false;
    }


}
