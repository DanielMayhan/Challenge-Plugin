package de.mayhan.challenge.freezesystem;

import de.mayhan.challenge.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class FreezeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (Main.isfreezed){
            commandSender.sendMessage(ChatColor.RED + "Already freezed!");
            return true;
        }
        Main.isfreezed = true;
        Bukkit.broadcastMessage(ChatColor.AQUA + "Alle Spiler sind jetzt gefreezed!");
        return true;
    }
}
