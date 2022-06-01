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

@CommandAlias("fly")
@CommandPermission("coreplugin.command.fly")
public class FlyCommand extends BaseCommand {

    @CatchUnknown
    public void fly(CommandSender sender, @Optional @CommandPermission("coreplugin.command.fly.others") OnlinePlayer player) {
        Player target = player == null ? null : player.player;
        if (target == null) {
            if(sender instanceof Player){
                target = (Player) sender;
            }else{
                sender.sendMessage(ChatColor.RED + "You must specify a player.");
                return;
            }
        }

        if (target.getAllowFlight()) {
            target.setAllowFlight(false);
            target.sendMessage(ChatColor.RED + "You are no longer flying.");
            sender.sendMessage(ChatColor.GREEN + "You have disabled flight for " + target.getName());
        }else{
            target.setAllowFlight(true);
            target.sendMessage(ChatColor.GREEN + "You can now fly!");
            sender.sendMessage(ChatColor.GREEN + "You have enabled flight for " + target.getName());
        }
    }
}
