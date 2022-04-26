package me.thatonedevil.beaconsmp.db;

import me.thatonedevil.beaconsmp.BeaconSmp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

import static me.thatonedevil.beaconsmp.BeaconSmp.format;

public class SetPlayerData implements CommandExecutor {

    private final BeaconSmp main;

    public SetPlayerData(BeaconSmp main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 3) {
                if (Bukkit.getPlayer(args[0]) != null) {

                    Player target = Bukkit.getPlayer(args[0]);
                    try {
                        CustomPlayer playerData = new CustomPlayer(main, target.getUniqueId());
                        switch (args[1].toLowerCase()) {
                            case "stars":
                                playerData.setStars(Double.parseDouble(args[2]));
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Set stars of &e" + target.getDisplayName() + " &6to &e" + args[2]));
                                break;
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                } else {
                    player.sendMessage(format("&c/setplayerdata [player] [stars] [stars: Integer]"));
                }
            } else {
                player.sendMessage(format("&c/setplayerdata [player] [stars] [stars: Integer]"));

            }
        }
        return false;
    }
}
