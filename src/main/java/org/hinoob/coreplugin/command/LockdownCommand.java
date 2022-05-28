package org.hinoob.coreplugin.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.hinoob.coreplugin.util.ConfigManager;
import org.hinoob.coreplugin.util.ConfigUtil;

import java.util.List;
import java.util.UUID;

@CommandAlias("lockdown")
@CommandPermission("coreplugin.command.lockdown")
public class LockdownCommand extends BaseCommand {

    @Subcommand("start|on|active")
    public void on(CommandSender sender){
        ConfigManager.set("lockdown.enabled", true);
        sender.sendMessage(ChatColor.GREEN + "Server is under lockdown now!");

        for(Player player : sender.getServer().getOnlinePlayers()){
            if(player.isOp()) continue;
            if(((List<String>)ConfigManager.get("lockdown.whitelist")).stream().anyMatch(s -> s.equalsIgnoreCase(player.getUniqueId().toString()))) continue;

            player.kickPlayer(ConfigManager.get("lockdown.message"));
        }
    }

    @Subcommand("stop|off|inactive")
    public void off(CommandSender sender){
        ConfigManager.set("lockdown.enabled", false);
        sender.sendMessage(ChatColor.GREEN + "Server is no longer under lockdown!");
    }

    @Subcommand("whitelist")
    public void whitelist(CommandSender sender){
        List<String> whitelist = (List<String>)ConfigManager.get("lockdown.whitelist");
        if(ConfigUtil.isListEmpty(whitelist)){
            sender.sendMessage(ChatColor.RED + "Whitelist is empty!");
        }else{
            sender.sendMessage(ChatColor.GREEN + "Whitelist:");
            for(String uuid : whitelist){
                if(uuid.equalsIgnoreCase("")) continue;
                OfflinePlayer player = Bukkit.getOfflinePlayer(UUID.fromString(uuid));
                sender.sendMessage(ChatColor.GREEN + "- " + player.getName());
            }
        }
    }

    @Subcommand("whitelist add")
    public void whadd(CommandSender sender, OfflinePlayer player){
        List<String> whitelist = (List<String>)ConfigManager.get("lockdown.whitelist");
        if(whitelist.stream().anyMatch(s -> s.equalsIgnoreCase(player.getUniqueId().toString()))){
            sender.sendMessage(ChatColor.RED + "Player is already in whitelist!");
            return;
        }
        whitelist.add(player.getUniqueId().toString());
        ConfigManager.set("lockdown.whitelist", whitelist);
        sender.sendMessage(ChatColor.GREEN + "Added " + player.getName() + " to whitelist!");
    }

    @Subcommand("whitelist remove")
    public void whremove(CommandSender sender, OfflinePlayer player){
        List<String> whitelist = (List<String>)ConfigManager.get("lockdown.whitelist");
        if(whitelist.stream().noneMatch(s -> s.equalsIgnoreCase(player.getUniqueId().toString()))){
            sender.sendMessage(ChatColor.RED + "Player is not in whitelist!");
            return;
        }
        whitelist.remove(player.getUniqueId().toString());
        ConfigManager.set("lockdown.whitelist", whitelist);
        sender.sendMessage(ChatColor.GREEN + "Removed " + player.getName() + " from whitelist!");
    }

}
