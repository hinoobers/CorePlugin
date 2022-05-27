package org.hinoob.coreplugin.util;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.hinoob.coreplugin.CorePlugin;

@UtilityClass
public class ConfigManager {

    public <T> T get(String key) {
        return (T) CorePlugin.getInstance().getConfig().get(key);
    }

    public void set(String key, Object value) {
        CorePlugin.getInstance().getConfig().set(key, value);
        save();
    }

    public void save(){
        CorePlugin.getInstance().saveConfig();
    }
}
