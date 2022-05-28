package org.hinoob.coreplugin.listener.impl;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.hinoob.coreplugin.CorePlugin;
import org.hinoob.coreplugin.listener.IListener;
import org.hinoob.coreplugin.util.ConfigManager;

import java.util.List;

public class PlayerLoginListener implements IListener, Listener {

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        if (ConfigManager.get("lockdown.enabled")) {
            if(event.getPlayer().isOp()) return;
            if(((List<String>)ConfigManager.get("lockdown.whitelist")).stream().anyMatch(s -> s.equalsIgnoreCase(event.getPlayer().getUniqueId().toString()))) return;

            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, ConfigManager.get("lockdown.message"));
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
