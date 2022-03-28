package me.thatonedevil.beaconsmp.Database;

import me.thatonedevil.beaconsmp.BeaconSmp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.UUID;

public class GetPlayerData implements CommandExecutor {

    private BeaconSmp main;

    public GetPlayerData(BeaconSmp main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 1) {
                if (Bukkit.getPlayer(args[0])  != null) {
                    Player target = Bukkit.getPlayer(args[0]);
                    try {
                        CustomPlayer playerData = new CustomPlayer(main, target.getUniqueId());
                        UUID uuid = playerData.getUuid();
                        String beaconLoc = playerData.getBeaconLoc();
                        int stars = playerData.getStars();

                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Data of " + target.getDisplayName() +
                                "\n &fUUID: &b" + uuid +
                                "\n  &fStars: &b" + stars +
                                "\n  &fBeacon Loc: &b" + beaconLoc));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
            }
            return false;
        }
        return false;
    }

}