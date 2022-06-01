package org.hinoob.coreplugin.listener.impl;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.hinoob.coreplugin.CorePlugin;

public class ModuleListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        CorePlugin.getInstance().getModules().stream().forEach(m -> m.onEvent(event));
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event){
        CorePlugin.getInstance().getModules().stream().forEach(m -> m.onEvent(event));
    }

    @EventHandler
    public void onPlayerLogout(PlayerQuitEvent event){
        CorePlugin.getInstance().getModules().stream().forEach(m -> m.onEvent(event));
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        CorePlugin.getInstance().getModules().stream().forEach(m -> m.onEvent(event));
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        CorePlugin.getInstance().getModules().stream().forEach(m -> m.onEvent(event));
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        CorePlugin.getInstance().getModules().stream().forEach(m -> m.onEvent(event));
    }

    @EventHandler
    public void onBlockDamage(BlockDamageEvent event){
        CorePlugin.getInstance().getModules().stream().forEach(m -> m.onEvent(event));
    }
}
