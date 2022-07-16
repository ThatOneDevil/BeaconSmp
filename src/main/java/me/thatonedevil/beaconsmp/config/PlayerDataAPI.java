// ALL MADE BY https://www.spigotmc.org/resources/playerdataapi.100492/
// COPIED OVER CODE DUE TO DEPENDENCY NOT WORKING
// MADE BY Bradley#8266 (SUBJECT TO CHANGE)
// https://discord.gg/sqxt98Zdvr

package me.thatonedevil.beaconsmp.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;


public class PlayerDataAPI {
    private File folder;

    public PlayerDataAPI(JavaPlugin plugin) {
        if (plugin != null) {
            this.setFolder(new File(plugin.getDataFolder(), "playerdata"));
        }

    }

    public void setFolder(File folder) {
        if (!folder.exists()) {
            folder.mkdirs();
        }

        if (!folder.isDirectory()) {
            throw new IllegalArgumentException("not a folder");
        } else {
            this.folder = folder;
        }
    }

    public YamlConfiguration create(OfflinePlayer player) {
        YamlConfiguration configuration = new YamlConfiguration();
        this.save(player, configuration);
        return configuration;
    }

    public boolean has(OfflinePlayer player) {
        return this.getFile(player) != null;
    }

    public File getFile(OfflinePlayer player) {
        this.ensureFolder();
        return new File(this.folder, player.getUniqueId() + ".yml");
    }


    public YamlConfiguration getOrCreate(OfflinePlayer player) {
        return this.has(player) ? this.get(player) : this.create(player);
    }

    public YamlConfiguration get(OfflinePlayer player) {
        File file = this.getFile(player);
        return !file.exists() ? null : YamlConfiguration.loadConfiguration(file);
    }

    public boolean save(OfflinePlayer player, YamlConfiguration configuration) {
        this.ensureFolder();

        try {
            configuration.save(new File(this.folder, player.getUniqueId() + ".yml"));
            return true;
        } catch (IOException var4) {
            return false;
        }
    }

    public boolean delete(OfflinePlayer player) {
        File file = this.getFile(player);
        return file != null ? file.delete() : true;
    }

    private void ensureFolder() {
        if (this.folder == null) {
            throw new IllegalArgumentException("folder has not been set");
        }
    }

}
