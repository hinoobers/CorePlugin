package org.hinoob.coreplugin;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.hinoob.coreplugin.database.DatabaseManager;
import org.hinoob.coreplugin.listener.IListener;
import org.hinoob.coreplugin.module.IModule;
import org.hinoob.coreplugin.task.ITask;
import org.hinoob.coreplugin.util.ReflectionUtil;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CorePlugin extends JavaPlugin {

    @Getter private static CorePlugin instance;

    @Getter private DatabaseManager databaseManager = new DatabaseManager();
    private List<Runnable> stopRunnables = new ArrayList<>();
    @Getter private List<IModule> modules = new ArrayList<>();

    @Override
    public void onEnable(){
        instance = this;
        saveDefaultConfig();

        // register tasks
        for(Class<?> taskClass : new Reflections("org.hinoob.coreplugin.task.impl").getSubTypesOf(ITask.class)){
            try {
                ITask task = (ITask) taskClass.newInstance();
                task.register(this);
                stopRunnables.add(task::stop); // So it will be stopped when the plugin is disabled
            }catch(Exception ex){
                getLogger().warning("Failed to register task: " + taskClass.getName());
            }
        }

        // register commands
        PaperCommandManager commandManager = new PaperCommandManager(this);
        for(Class<?> commandClass : new Reflections("org.hinoob.coreplugin.command").getSubTypesOf(BaseCommand.class)){
            try {
                BaseCommand command = (BaseCommand) commandClass.newInstance();
                commandManager.registerCommand(command);
                stopRunnables.add(() -> commandManager.unregisterCommand(command));
            }catch(Exception ex){
                getLogger().warning("Failed to register command: " + commandClass.getName());
            }
        }

        // register modules
        for(Class<?> moduleClass : new Reflections("org.hinoob.coreplugin.module").getSubTypesOf(IModule.class)){
            try {
                IModule module = (IModule) moduleClass.newInstance();
                module.init(this);

                modules.add(module);
            }catch(Exception ex){
                getLogger().warning("Failed to register module: " + moduleClass.getName());
            }
        }

        // register listeners
        for(Class<?> listenerClass : new Reflections("org.hinoob.coreplugin.listener.impl").getSubTypesOf(IListener.class)){
            try {
                IListener listener = (IListener) listenerClass.newInstance();
                if(listener.shouldRegister(this)){
                    listener.register(this);
                }
            }catch(Exception ex){
                getLogger().warning("Failed to register listener: " + listenerClass.getName());
            }
        }

        // register managers
        databaseManager.init(this);
        if(!databaseManager.hasAvailableDatabase()){
            getLogger().severe("No available database found! (Disabling plugin)");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        getLogger().info("CorePlugin has been enabled!");
    }

    @Override
    public void onDisable(){
        stopRunnables.forEach(Runnable::run);
        HandlerList.unregisterAll(this);

        getLogger().info("CorePlugin has been disabled! (Ran " + stopRunnables.size() + " stop task(s))");
    }
}
