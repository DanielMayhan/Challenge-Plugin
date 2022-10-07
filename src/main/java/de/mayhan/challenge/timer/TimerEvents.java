package de.mayhan.challenge.timer;


import de.mayhan.challenge.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class TimerEvents implements Listener{

    private Main main;
    public TimerEvents(Main main) {
        this.main = main;
    }

    private final Configuration config = main.getConfig();

    @EventHandler(ignoreCancelled = true)
    public void onEntityDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();

            if (!Timer.isRunning) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onEntityDeath(EntityDeathEvent e) {
        if (!TimerCommand.timers.isEmpty()) {
            Timer timer = TimerCommand.timers.get(1);
            if (e.getEntity() instanceof EnderDragon) {
                timer.pause();

                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage(ChatColor.RED + "========================================");
                Bukkit.broadcastMessage(ChatColor.RED + ChatColor.BOLD.toString() + "Challenge beendet nach: " + ChatColor.GREEN + Timer.getTime);
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

    @EventHandler(ignoreCancelled = true)
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player player = e.getEntity();
        if (TimerCommand.timers.isEmpty()){
            return;
        }
        if (config.getBoolean("team-death")){
            Timer timer = TimerCommand.timers.get(1);
            timer.pause();

            Bukkit.broadcastMessage("");
            Bukkit.broadcastMessage(ChatColor.RED + "========================================");
            Bukkit.broadcastMessage(ChatColor.RED + ChatColor.BOLD.toString() + "Challenge beendet, weil " + ChatColor.GREEN + player.getDisplayName() + ChatColor.RED + ChatColor.BOLD.toString() + " gestorben ist!");
            Bukkit.broadcastMessage(ChatColor.RED + "Zeit: " + Timer.getTime);
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


    @EventHandler(ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        if (!Timer.isRunning){
            e.setCancelled(true);
        }
    }


}