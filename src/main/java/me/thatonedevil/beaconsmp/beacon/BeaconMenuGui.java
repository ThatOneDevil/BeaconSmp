package me.thatonedevil.beaconsmp.beacon;

import me.thatonedevil.beaconsmp.utilis.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static me.thatonedevil.beaconsmp.BeaconSmp.format;

public class BeaconMenuGui implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Inventory inv = Bukkit.createInventory(null, 27, format("&bBeacon Menu"));

            ItemStack itemStack = new ItemBuilder(Material.OAK_SIGN, 1)
                    .setDisplayName("&3Click to open &bshop").build();
            inv.setItem(11, itemStack);

            player.openInventory(inv);
        }
        return false;
    }


}

