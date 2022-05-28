package org.hinoob.coreplugin.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CatchUnknown;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Optional;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.hinoob.coreplugin.util.ConfigManager;
import org.hinoob.coreplugin.util.ConfigUtil;

@CommandAlias("spawn")
@CommandPermission("coreplugin.command.spawn")
public class SpawnCommand extends BaseCommand {

    @CatchUnknown
    public void spawn(CommandSender sender, @Optional @CommandPermission("coreplugin.command.spawn.others") OnlinePlayer player) {
        if(sender instanceof Player){
            if(player == null){
                ((Player) sender).teleport(ConfigUtil.getLocation(ConfigManager.get("spawn.location")));
            }else{
                player.getPlayer().teleport(ConfigUtil.getLocation(ConfigManager.get("spawn.location")));
            }
        }else{
            if(player == null){
                sender.sendMessage(ChatColor.RED + "You must specify a player.");
            }else{
                player.getPlayer().teleport(ConfigUtil.getLocation(ConfigManager.get("spawn.location")));
            }
        }
    }
}
