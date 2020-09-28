package com.github.netricecake.discord4m.listener;

import com.github.netricecake.discord4m.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class EventListener implements Listener {

    private Main main;

    public EventListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerChat(PlayerChatEvent event) {
        this.main.onMinecraftChat(event.getPlayer().getName(), event.getMessage(), event.getPlayer().getUniqueId().toString());
    }

}
