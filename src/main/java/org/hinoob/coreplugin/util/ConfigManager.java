package org.hinoob.coreplugin.util;

import lombok.experimental.UtilityClass;
import org.bukkit.*;
import org.hinoob.coreplugin.CorePlugin;

@UtilityClass
public class ConfigManager {

    public <T> T get(String key) {
        T obj =  (T) CorePlugin.getInstance().getConfig().get(key);
        if(obj instanceof String){
            return (T) ChatColor.translateAlternateColorCodes('&', (String) obj);
        }
        return obj;
    }

    public void set(String key, Object value) {
        CorePlugin.getInstance().getConfig().set(key, value);
        save();
    }

    public void save(){
        CorePlugin.getInstance().saveConfig();
    }
}
