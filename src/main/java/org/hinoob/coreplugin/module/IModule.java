package org.hinoob.coreplugin.module;

import org.bukkit.event.Event;
import org.bukkit.scheduler.BukkitScheduler;
import org.hinoob.coreplugin.CorePlugin;

public interface IModule {

    void init(CorePlugin plugin);
    void tick(CorePlugin plugin);

    void onEvent(Event event);
    void startTicking(BukkitScheduler scheduler);

}
