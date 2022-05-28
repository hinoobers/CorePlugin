package org.hinoob.coreplugin.task.impl;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.hinoob.coreplugin.CorePlugin;
import org.hinoob.coreplugin.task.ITask;
import org.hinoob.coreplugin.user.User;
import org.hinoob.coreplugin.user.UserManager;

public class UserSaveTask implements ITask, Runnable {

    private BukkitTask task;

    @Override
    public void run() {
        for(Player player : Bukkit.getOnlinePlayers()){
            User user = UserManager.getUser(player.getUniqueId());
            if(user == null) continue;

            user.save();
        }
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
