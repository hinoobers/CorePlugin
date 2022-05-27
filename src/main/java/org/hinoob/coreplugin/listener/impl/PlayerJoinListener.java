package org.hinoob.coreplugin.listener.impl;

import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.hinoob.coreplugin.CorePlugin;
import org.hinoob.coreplugin.listener.IListener;
import org.hinoob.coreplugin.user.UserManager;
import org.hinoob.coreplugin.util.ConfigManager;
import org.hinoob.coreplugin.util.ConfigUtil;

public class PlayerJoinListener implements IListener, Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        UserManager.getUser(event.getPlayer().getUniqueId()).load();

        if((boolean)ConfigManager.get("spawn.on-join") && (boolean)ConfigManager.get(ConfigManager.get("spawn.location.set"))){
            event.getPlayer().teleport(ConfigUtil.getLocation(ConfigManager.get("spawn.location")));
        }
    }

    @Override
    public boolean shouldRegister(CorePlugin plugin) {
        return true;
    }

    @Override
    public void register(CorePlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
}
