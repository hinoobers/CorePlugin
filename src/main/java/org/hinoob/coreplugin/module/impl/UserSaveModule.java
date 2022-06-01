package org.hinoob.coreplugin.module.impl;

import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitScheduler;
import org.hinoob.coreplugin.CorePlugin;
import org.hinoob.coreplugin.module.IModule;
import org.hinoob.coreplugin.module.Module;
import org.hinoob.coreplugin.user.UserManager;

public class UserSaveModule extends Module implements IModule {
    @Override
    public void init(CorePlugin plugin) {

    }

    @Override
    public void tick(CorePlugin plugin) {

    }

    @Override
    public void onEvent(Event event) {
        if(event instanceof PlayerQuitEvent) {
            PlayerQuitEvent e = (PlayerQuitEvent) event;
            UserManager.getUser(e.getPlayer().getUniqueId()).save();
        }
    }

    @Override
    public void startTicking(BukkitScheduler scheduler) {

    }
}
