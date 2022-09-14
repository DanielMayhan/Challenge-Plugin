package de.mayhan.challenge.timer;

import com.google.common.collect.Maps;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TimerCommand implements TabExecutor {

    public static HashMap<Integer, Timer> timers = Maps.newHashMap();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "Du bist kein Spieler!");
            return false;
        }


        Player player = (Player) sender;
        if (args.length == 1 && args[0].equalsIgnoreCase("start")){

            Timer timer = new Timer();
            if (Timer.isrunning){
                player.sendMessage(ChatColor.RED + "Timer läuft bereits!");
                return true;
            }


            List<Player> players = new ArrayList<>();
            for (Player p : Bukkit.getOnlinePlayers()){
                players.add(p);
            }

            timer.start(players);
            Timer.isrunning = true;
            timers.put(1, timer);

            for (Player p : Bukkit.getOnlinePlayers()){
                Location l = p.getLocation();
                p.playSound(p, Sound.ENTITY_ENDER_DRAGON_GROWL, 5, 0);
            }

            Bukkit.broadcastMessage(ChatColor.GREEN + ChatColor.BOLD.toString() + "TIMER GESTARTED!");
            return true;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("stop")){
            Timer timer = timers.get(1);
            timers.remove(1);
            timer.stop();
            Timer.isrunning = false;
            Bukkit.broadcastMessage(ChatColor.RED + ChatColor.BOLD.toString() + "TIMER GESTOPPT!");
            return true;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("pause")){
            Timer timer = timers.get(1);
            timer.pause();
            Timer.isrunning = false;
            Bukkit.broadcastMessage(ChatColor.RED + "Timer pausiert!");
            return true;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("resume")){
            Timer timer = timers.get(1);
            timer.resume();
            Timer.isrunning = true;
            Bukkit.broadcastMessage(ChatColor.RED + "Timer fortgesetzt!");
            return true;
        }

        player.sendMessage("Usage: /timer <start|stop|pause|resume>");

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> strings = new ArrayList<>();

        strings.add("start");
        strings.add("stop");
        strings.add("pause");
        strings.add("resume");

        return strings;
    }
}
