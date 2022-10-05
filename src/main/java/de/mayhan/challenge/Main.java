package de.mayhan.challenge;

import de.mayhan.challenge.systemevents.PlayerConnectionEvent;
import de.mayhan.challenge.timer.TimerCommand;
import de.mayhan.challenge.timer.TimerEvents;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public final class Main extends JavaPlugin {

    private static final PluginManager pluginManager = Bukkit.getServer().getPluginManager();

    @Override
    public void onEnable() {
        system();
        timer();
        Bukkit.getLogger().info("====================================");
        Bukkit.getLogger().info(" Manhunt-Plugin successfully loaded! ");
        Bukkit.getLogger().info("====================================");

    }

    @Override
    public void onDisable() {

    }
    public void system(){
        pluginManager.registerEvents(new PlayerConnectionEvent(), this);
    }
    public void timer(){
        pluginManager.registerEvents(new TimerEvents(), this);
        getCommand("timer").setExecutor(new TimerCommand());
        }
    }






/*
Added since last update (v1.1):


 */