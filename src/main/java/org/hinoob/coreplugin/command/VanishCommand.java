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
import org.hinoob.coreplugin.user.User;
import org.hinoob.coreplugin.user.UserManager;
import org.hinoob.coreplugin.util.ConfigManager;
import org.hinoob.coreplugin.util.ConfigUtil;
import org.hinoob.coreplugin.util.PlayerHider;

@CommandAlias("vanish|v")
@CommandPermission("coreplugin.command.vanish")
public class VanishCommand extends BaseCommand {

    @CatchUnknown
    public void vanish(CommandSender sender, @Optional @CommandPermission("coreplugin.command.vanish.others") OnlinePlayer player) {
        Player target = player == null ? null : player.player;
        if (target == null) {
            if(sender instanceof Player){
                target = (Player) sender;
            }else{
                sender.sendMessage(ChatColor.RED + "You must specify a player.");
                return;
            }
        }

        User targetUser = UserManager.getUser(target.getUniqueId());
        if(targetUser.getUserVanishManager().isVanished()){
            target.sendMessage(ChatColor.RED + "You are now visible.");
            targetUser.getUserVanishManager().setVanished(false);
            PlayerHider.hidePlayerFromEveryone(target);
        }else{
            target.sendMessage(ChatColor.GREEN + "You are now invisible.");
            targetUser.getUserVanishManager().setVanished(true);
            PlayerHider.showPlayerToEveryone(target);
        }
    }
}
