package me.thatonedevil.beaconsmp.db;

import me.thatonedevil.beaconsmp.BeaconSmp;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

import static me.thatonedevil.beaconsmp.BeaconSmp.format;


public class ResetPlayerData implements CommandExecutor {

    private BeaconSmp main;

    public ResetPlayerData(BeaconSmp main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 1) {
                if (Bukkit.getPlayer(args[0]) != null) {
                    Player target = Bukkit.getPlayer(args[0]);
                    try {
                        CustomPlayer playerData = new CustomPlayer(main, target.getUniqueId());
                        playerData.deletePlayerData(target.getUniqueId());
                        player.sendMessage(format("&cDeleted &6" + target.getDisplayName() + "&c's Data!"));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
        return false;
    }

}
