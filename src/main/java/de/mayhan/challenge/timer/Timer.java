package de.mayhan.challenge.timer;


import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;


import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Timer {

    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(0);
    private boolean paused;
    private int elapsedtime;

    public static String getTime;

    public void start(List<Player> players){


        executorService.scheduleAtFixedRate(() -> {
            if (!paused){
                elapsedtime += 1000;
            }


            int hour = (elapsedtime / 3600000);
            int min = (elapsedtime / 60000) % 60;
            int sec = (elapsedtime / 1000) % 60;

            StringBuilder message = new StringBuilder(ChatColor.GOLD + "");
            message.append(String.format("%02d", hour)).append(":");
            message.append(String.format("%02d", min)).append(":");
            message.append(String.format("%02d", sec));

            if (paused){
                message.append(ChatColor.GOLD + " (paused)");
            }

            getTime = String.valueOf(message);

            for (Player p : players){
                sendActionbar(p, message.toString());
            }
            }, 0, 1000, TimeUnit.MILLISECONDS);


    }

    public void stop(){
        executorService.shutdownNow();
    }

    public void pause(){
        this.paused = true;
    }
    public void resume(List<Player> players){
        this.paused = false;
        for (Player p : players){
            sendActionbar(p, getTime.toString());
        }
    }

    private void sendActionbar(Player player, String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
    }

    public static boolean isRunning = false;
}
