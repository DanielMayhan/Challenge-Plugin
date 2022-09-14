package de.mayhan.challenge.timer;


import de.mayhan.challenge.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class TimerEvents implements Listener {


    @EventHandler(ignoreCancelled = true)
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if (!Timer.isRunning) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onEntityDeath(EntityDeathEvent event) {
        if (!TimerCommand.timers.isEmpty()) {
            Timer timer = TimerCommand.timers.get(1);
            if (event.getEntity() instanceof EnderDragon){
                timer.pause();

                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage(ChatColor.RED + "========================================");
                Bukkit.broadcastMessage(ChatColor.RED + ChatColor.BOLD.toString() + "Challenge beendet nach: " + ChatColor.GREEN + Timer.getTime);
                Bukkit.broadcastMessage(ChatColor.RED + "========================================");
                Bukkit.broadcastMessage("");

                for (Player p : Bukkit.getOnlinePlayers()){
                    p.setGameMode(GameMode.SPECTATOR);

                    Location loc = p.getLocation().clone();
                    Inventory inv = p.getInventory();

                    for (ItemStack item : inv.getContents()){
                        if (item != null){
                            loc.getWorld().dropItemNaturally(loc, item.clone());
                        }
                    }
                    inv.clear();
                }
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerDeath(PlayerDeathEvent e) {
        if (!TimerCommand.timers.isEmpty()){
            Timer timer = TimerCommand.timers.get(1);
            timer.pause();

            Bukkit.broadcastMessage("");
            Bukkit.broadcastMessage(ChatColor.RED + "========================================");
            Bukkit.broadcastMessage(ChatColor.RED + ChatColor.BOLD.toString() + "Challenge beendet nach: " + ChatColor.GREEN + Timer.getTime);
            Bukkit.broadcastMessage(ChatColor.RED + ChatColor.BOLD.toString() + ",weil " + ChatColor.RESET + ChatColor.WHITE + e.getEntity().getName() + ChatColor.RED + ChatColor.BOLD.toString() + " gestorben ist!");
            Bukkit.broadcastMessage(ChatColor.RED + "========================================");
            Bukkit.broadcastMessage("");

            for (Player p : Bukkit.getOnlinePlayers()) {
                p.setGameMode(GameMode.SPECTATOR);

                Location loc = p.getLocation().clone();
                Inventory inv = p.getInventory();

                for (ItemStack item : inv.getContents()) {
                    if (item != null) {
                        loc.getWorld().dropItemNaturally(loc, item.clone());
                    }
                }
                inv.clear();
            }
        }
    }


}