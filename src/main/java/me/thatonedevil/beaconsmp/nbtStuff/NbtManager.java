package me.thatonedevil.beaconsmp.nbtStuff;

import me.thatonedevil.beaconsmp.BeaconSmp;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;

import static me.thatonedevil.beaconsmp.BeaconSmp.format;


public class NbtManager {

    /**
     * @param item requires ItemStack to get value from.
     * @param key requires a string to get value from.
     * @return the ItemBuilder.
     */

    public static Double getData(ItemStack item, String key) {
        NamespacedKey nkey = new NamespacedKey(BeaconSmp.getPlugin(BeaconSmp.class), key);
        ItemMeta itemMeta = item.getItemMeta();
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        if (container.has(nkey, PersistentDataType.DOUBLE)) {
            return container.get(nkey, PersistentDataType.DOUBLE);
        } else {
            return null;

        }
    }

    /**
     * @param item requires ItemStack to get value from.
     * @param value needs a value to set to ItemStack.
     * @param key requires a string to get value from.
     * @return the ItemBuilder.
     */

    public static void setData(Double value, ItemStack item, String key) {
        NamespacedKey nkey = new NamespacedKey(BeaconSmp.getPlugin(BeaconSmp.class), key);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.getPersistentDataContainer().set(nkey, PersistentDataType.DOUBLE, value);
        itemMeta.setLore(Arrays.asList(format("&6Value: &e" + value)));
        item.setItemMeta(itemMeta);


    }

}
