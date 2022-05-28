package org.hinoob.coreplugin.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

public class ConfigUtil {

    public static Location getLocation(ConfigurationSection section) {
        World world = null;
        if (section.isSet("world")) {
            world = Bukkit.getWorld(section.getString("world"));
            if(world == null){
                world = new WorldCreator(section.getString("world")).createWorld();
            }
        }

        double x = section.getDouble("x");
        double y = section.getDouble("y");
        double z = section.getDouble("z");
        float yaw = (float) section.getDouble("yaw");
        float pitch = (float) section.getDouble("pitch");
        return new Location(world, x, y, z, yaw, pitch);
    }

    public static boolean isListEmpty(List<String> str){
        // actually check
        if(str.isEmpty()) return true;
        if(str.stream().filter(s -> s.replaceAll(" ", "").isEmpty()).count() == 0) return true;
        if(str.stream().allMatch(s -> s.length() == 0)) return true;

        return false;
    }
}
