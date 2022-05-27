package org.hinoob.coreplugin.user;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@UtilityClass
public class UserManager {

    private Map<UUID, User> userMap = new HashMap<>();

    public User getUser(UUID uuid) {
        if(Bukkit.getPlayer(uuid) == null) {
            return new User(uuid);
        }else{
            User user = userMap.getOrDefault(uuid, null);
            if(user == null) {
                user = new User(uuid);
                userMap.put(uuid, user);
            }
            return user;
        }
    }
}
