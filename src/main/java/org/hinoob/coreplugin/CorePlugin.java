package org.hinoob.coreplugin;

import lombok.Getter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.hinoob.coreplugin.listener.IListener;
import org.hinoob.coreplugin.task.ITask;
import org.hinoob.coreplugin.util.ReflectionUtil;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.Optional;

public class CorePlugin extends JavaPlugin {

    @Getter private static CorePlugin instance;

    @Override
    public void onEnable(){
        instance = this;

        // register tasks
        for(Class<?> taskClass : new Reflections("org.hinoob.coreplugin.task.impl").getSubTypesOf(ITask.class)){
            try {
                ITask task = (ITask) taskClass.newInstance();
                task.register(this);
            }catch(Exception ex){
                getLogger().warning("Failed to register task: " + taskClass.getName());
            }
        }

        // register listeners
        for(Class<?> listenerClass : new Reflections("org.hinoob.coreplugin.listener.impl").getSubTypesOf(Listener.class)){
            try {
                IListener listener = (IListener) listenerClass.newInstance();
                if(listener.shouldRegister(this)){
                    listener.register(this);
                }
            }catch(Exception ex){
                getLogger().warning("Failed to register listener: " + listenerClass.getName());
            }
        }

        getLogger().info("CorePlugin has been enabled!");
    }

    @Override
    public void onDisable(){
        getLogger().info("CorePlugin has been disabled!");
    }
}
