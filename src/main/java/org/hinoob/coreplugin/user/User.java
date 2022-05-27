package org.hinoob.coreplugin.user;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.hinoob.coreplugin.CorePlugin;
import org.hinoob.coreplugin.database.IDatabase;

import java.util.UUID;

public class User {

    @Getter private final UUID uuid;

    public User(UUID uuid) {
        this.uuid = uuid;
    }

    public void load(){
        IDatabase currentDatabase = CorePlugin.getInstance().getDatabaseManager().getCurrentDatabase();
        if(currentDatabase.userExists(uuid)){
            currentDatabase.createUser(uuid);
        }
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }
}
