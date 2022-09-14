package de.mayhan.challenge;

import de.mayhan.challenge.timer.TimerCommand;
import de.mayhan.challenge.timer.TimerEvents;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public final class Main extends JavaPlugin {



    @Override
    public void onEnable() {

        systemcommands();
        timer();

    }

    @Override
    public void onDisable() {

    }


    public void systemcommands(){

    }
    public void timer(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new TimerEvents(), this);
        getCommand("timer").setExecutor(new TimerCommand());
    }
}
