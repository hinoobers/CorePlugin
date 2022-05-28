package org.hinoob.coreplugin.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Optional;
import co.aikar.commands.annotation.Subcommand;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("gamemode|gm")
@CommandPermission("coreplugin.command.gamemode")
public class GameModeCommand extends BaseCommand {

    @Subcommand("creative|c|1")
    @CommandPermission("coreplugin.command.gamemode.creative")
    public void creative(CommandSender sender, @Optional @CommandPermission("coreplugin.command.gamemode.others") OnlinePlayer player) {
        Player target = player == null ? null : player.player;
        if (target == null) {
            if(sender instanceof Player){
                target = (Player) sender;
            }else{
                sender.sendMessage(ChatColor.RED + "You must specify a player.");
                return;
            }
        }

        target.setGameMode(org.bukkit.GameMode.CREATIVE);
    }

    @Subcommand("survival|s|0")
    @CommandPermission("coreplugin.command.gamemode.survival")
    public void survival(CommandSender sender, @Optional @CommandPermission("coreplugin.command.gamemode.others") OnlinePlayer player) {
        Player target = player == null ? null : player.player;
        if (target == null) {
            if(sender instanceof Player){
                target = (Player) sender;
            }else{
                sender.sendMessage(ChatColor.RED + "You must specify a player.");
                return;
            }
        }

        target.setGameMode(org.bukkit.GameMode.SURVIVAL);
    }

    @Subcommand("adventure|a|2")
    @CommandPermission("coreplugin.command.gamemode.adventure")
    public void adventure(CommandSender sender, @Optional @CommandPermission("coreplugin.command.gamemode.others") OnlinePlayer player) {
        Player target = player == null ? null : player.player;
        if (target == null) {
            if(sender instanceof Player){
                target = (Player) sender;
            }else{
                sender.sendMessage(ChatColor.RED + "You must specify a player.");
                return;
            }
        }

        target.setGameMode(org.bukkit.GameMode.ADVENTURE);
    }

    @Subcommand("spectator|sp|3")
    @CommandPermission("coreplugin.command.gamemode.spectator")
    public void spectator(CommandSender sender, @Optional @CommandPermission("coreplugin.command.gamemode.others") OnlinePlayer player) {
        Player target = player == null ? null : player.player;
        if (target == null) {
            if(sender instanceof Player){
                target = (Player) sender;
            }else{
                sender.sendMessage(ChatColor.RED + "You must specify a player.");
                return;
            }
        }

        target.setGameMode(org.bukkit.GameMode.SPECTATOR);
    }
}
