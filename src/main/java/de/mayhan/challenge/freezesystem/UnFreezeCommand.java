package de.mayhan.challenge.freezesystem;

import de.mayhan.challenge.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class UnFreezeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!Main.isfreezed){
            commandSender.sendMessage(ChatColor.RED + "Already unfreezed!");
            return true;
        }
        Main.isfreezed = true;
        Bukkit.broadcastMessage(ChatColor.AQUA + "Ihr könnt euch wieder bewegen");
        return true;
    }
}
