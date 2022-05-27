package org.hinoob.coreplugin.listener;

import org.bukkit.event.Listener;
import org.hinoob.coreplugin.CorePlugin;

public interface IListener {

    default boolean shouldRegister(CorePlugin plugin){
        return true;
    }

    void register(CorePlugin plugin);
}
