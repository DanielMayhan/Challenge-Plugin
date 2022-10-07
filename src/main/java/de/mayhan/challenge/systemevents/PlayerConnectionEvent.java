package de.mayhan.challenge.systemevents;

import com.google.gson.FieldAttributes;
import de.mayhan.challenge.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;



public class PlayerConnectionEvent implements Listener {

    private Main main;
    public PlayerConnectionEvent(Main main) {
        this.main = main;
    }
    private final Configuration config = main.getConfig();


    @EventHandler(ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        if (!config.getBoolean("custom-messages")){
            return;
        }
        if (config.get("custom-motd") == null){
            player.sendMessage(ChatColor.GOLD + "Welcome to the Server!");
        } else {
            player.sendMessage(config.getString("custom-motd"));
        }


        e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + ChatColor.GREEN + player.getName() + ChatColor.WHITE + " joined.");
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerQuit(PlayerQuitEvent e) {

        if (!config.getBoolean("custom-messages")){
            return;
        }

        Player player = e.getPlayer();
        e.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + ChatColor.RED + player.getName() + ChatColor.WHITE + " quit.");
    }
}
