package me.thatonedevil.beaconsmp.economy;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static me.thatonedevil.beaconsmp.BeaconSmp.format;
import static me.thatonedevil.beaconsmp.nbtStuff.NbtManager.setData;

public class SetValue implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            ItemStack item = player.getItemInHand();

            if (args.length == 1) {
                if (item.getType() != Material.AIR) {
                    setData(Double.valueOf(args[0]), item, "Value");
                    player.sendMessage(format("&6Value &e" + args[0] + " &6has been added to your item"));

                } else {
                    player.sendMessage(format("&cUsage: /setValue [value]"));
                }

            }
        }
        return false;
    }
}
