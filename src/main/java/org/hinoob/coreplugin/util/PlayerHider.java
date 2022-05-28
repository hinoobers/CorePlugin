package org.hinoob.coreplugin.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerHider {

    public static void hidePlayerFromEveryone(Player toHide){
        for(Player otherPlayer : Bukkit.getOnlinePlayers()){
            if(otherPlayer.equals(toHide)) continue; // don't hide yourself
            if(otherPlayer.isOp()) continue;

            otherPlayer.hidePlayer(toHide);
        }
    }

    public static void showPlayerToEveryone(Player toShow){
        for(Player otherPlayer : Bukkit.getOnlinePlayers()){
            if(otherPlayer.canSee(toShow)) continue; // can see them already

            otherPlayer.showPlayer(toShow);
        }
    }
}
