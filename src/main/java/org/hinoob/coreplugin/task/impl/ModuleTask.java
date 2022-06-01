package org.hinoob.coreplugin.task.impl;

import org.bukkit.scheduler.BukkitTask;
import org.hinoob.coreplugin.CorePlugin;
import org.hinoob.coreplugin.module.IModule;
import org.hinoob.coreplugin.task.ITask;

public class ModuleTask implements ITask, Runnable {

    private BukkitTask task;

    @Override
    public void run() {
        CorePlugin.getInstance().getModules().forEach(i -> i.tick(CorePlugin.getInstance()));
    }

    @Override
    public void register(CorePlugin corePlugin) {
        this.task = corePlugin.getServer().getScheduler().runTaskTimer(corePlugin, this, 0L,20L);
    }

    @Override
    public void stop() {
        task.cancel();
    }
}
