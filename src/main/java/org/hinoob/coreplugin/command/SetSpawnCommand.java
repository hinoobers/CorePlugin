package org.hinoob.coreplugin.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CatchUnknown;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import org.bukkit.entity.Player;
import org.hinoob.coreplugin.util.ConfigManager;

@CommandAlias("setspawn")
@CommandPermission("coreplugin.command.setspawn")
public class SetSpawnCommand extends BaseCommand {

    @CatchUnknown
    public void setspawn(Player player){
        ConfigManager.set("spawn.location.x", player.getLocation().getX());
        ConfigManager.set("spawn.location.y", player.getLocation().getY());
        ConfigManager.set("spawn.location.z", player.getLocation().getZ());
        ConfigManager.set("spawn.location.yaw", player.getLocation().getYaw());
        ConfigManager.set("spawn.location.pitch", player.getLocation().getPitch());
        ConfigManager.set("spawn.location.world", player.getLocation().getWorld().getName());
        ConfigManager.set("spawn.location.set", true);
    }
}
