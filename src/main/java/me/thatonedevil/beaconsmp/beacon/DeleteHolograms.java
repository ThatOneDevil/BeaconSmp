package me.thatonedevil.beaconsmp.beacon;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import me.thatonedevil.beaconsmp.BeaconSmp;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeleteHolograms implements CommandExecutor {

    private BeaconSmp main;

    public DeleteHolograms(BeaconSmp main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            for (Hologram hologram : HologramsAPI.getHolograms(main)) {
                hologram.delete();
                player.sendMessage("Deleted all holograms!");
            }
        }
        return false;

    }
}
