package org.hinoob.coreplugin.user;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.hinoob.coreplugin.CorePlugin;
import org.hinoob.coreplugin.database.IDatabase;
import org.hinoob.coreplugin.user.manager.impl.UserVanishManager;

import java.util.UUID;

public class User {

    @Getter private final UUID uuid;

    public User(UUID uuid) {
        this.uuid = uuid;
    }

    @Getter private UserVanishManager userVanishManager = new UserVanishManager(this);

    public void load(){
        IDatabase currentDatabase = CorePlugin.getInstance().getDatabaseManager().getCurrentDatabase();
        if(currentDatabase.userExists(uuid)){
            currentDatabase.createUser(uuid);
        }

        userVanishManager.load(currentDatabase);
    }

    public void save(){
        IDatabase currentDatabase = CorePlugin.getInstance().getDatabaseManager().getCurrentDatabase();

        userVanishManager.save(currentDatabase);
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }
}
