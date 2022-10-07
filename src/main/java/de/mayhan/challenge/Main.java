package de.mayhan.challenge;

import de.mayhan.challenge.freezesystem.FreezeCommand;
import de.mayhan.challenge.freezesystem.FreezeListener;
import de.mayhan.challenge.freezesystem.UnFreezeCommand;
import de.mayhan.challenge.systemevents.PlayerConnectionEvent;
import de.mayhan.challenge.timer.TimerCommand;
import de.mayhan.challenge.timer.TimerEvents;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public final class Main extends JavaPlugin {



    private static final PluginManager pluginManager = Bukkit.getServer().getPluginManager();

    public static boolean isfreezed;

    @Override
    public void onEnable() {
        loadConfig();
        freezeSystem();
        system();
        timer();
        Bukkit.getLogger().info("====================================");
        Bukkit.getLogger().info(" Manhunt-Plugin successfully loaded! ");
        Bukkit.getLogger().info("====================================");

    }

    private void loadConfig() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {

    }
    public void system(){
        pluginManager.registerEvents(new PlayerConnectionEvent(), this);
    }
    public void timer(){
        pluginManager.registerEvents(new TimerEvents(this), this);
        getCommand("timer").setExecutor(new TimerCommand());
        }

    public void freezeSystem() {
        pluginManager.registerEvents(new FreezeListener(), this);
        getCommand("freeze").setExecutor(new FreezeCommand());
        getCommand("unfreeze").setExecutor(new UnFreezeCommand());
        isfreezed = getConfig().getBoolean("freeze-on-start");
    }
}

