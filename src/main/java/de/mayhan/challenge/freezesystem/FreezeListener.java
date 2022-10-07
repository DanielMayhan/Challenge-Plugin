package de.mayhan.challenge.freezesystem;

import de.mayhan.challenge.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class FreezeListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onPlayerMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if ((!player.isOp()) && Main.isfreezed){
            e.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        if ((!player.isOp()) && Main.isfreezed){
            e.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onEntityDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof  Player){
            Player player = (Player) e.getEntity();
            if ((!player.isOp()) && Main.isfreezed){
                e.setCancelled(true);
            }
        }
    }

}
