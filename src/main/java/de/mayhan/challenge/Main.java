package de.mayhan.challenge;

import de.mayhan.challenge.timer.Timer;
import de.mayhan.challenge.timer.TimerCommand;
import de.mayhan.challenge.timer.TimerEvents;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Time;

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
