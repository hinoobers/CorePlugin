package org.hinoob.coreplugin.module;

import org.bukkit.scheduler.BukkitTask;

public class Module {

    protected BukkitTask task;

    public void stopRunnable(){
        if (task != null) {
            task.cancel();
            task = null;
        }
    }

}
