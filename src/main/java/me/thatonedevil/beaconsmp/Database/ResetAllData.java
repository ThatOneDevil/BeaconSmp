package me.thatonedevil.beaconsmp.Database;

import me.thatonedevil.beaconsmp.BeaconSmp;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ResetAllData implements CommandExecutor {

    private BeaconSmp main;

    public ResetAllData(BeaconSmp main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage(ChatColor.RED + "This command can only be run by console!");
        }else {
            resetData();
            System.out.println("All player data has been deleted!");
        }
        return false;
    }
    private void resetData() {
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement("SELECT UUID FROM players");
            ResultSet rs = statement.executeQuery();
            UUID uuid = null;
            while (rs.next()) {
                uuid = UUID.fromString(rs.getString("UUID"));
            }
            PreparedStatement statement2 = main.getDatabase().getConnection().prepareStatement("DELETE from players WHERE UUID = '" + uuid + "';");
            statement2.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
