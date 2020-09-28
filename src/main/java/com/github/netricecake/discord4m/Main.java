package com.github.netricecake.discord4m;

import com.github.netricecake.discord4m.discord.DiscordBot;
import com.github.netricecake.discord4m.discord.WebHook;
import com.github.netricecake.discord4m.listener.EventListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

public class Main extends JavaPlugin {

    private DiscordBot bot;
    private EventListener listener;
    private WebHook webHook;

    private Logger logger;

    @Override
    public void onEnable() {
        saveConfig();

        File configFile = new File(getDataFolder(), "config.yml");
        if (configFile.length() == 0) {
            getConfig().options().copyDefaults(true);
            saveConfig();

            return;
        }

        this.bot = new DiscordBot(this);
        this.bot.start();

        this.listener = new EventListener(this);
        getServer().getPluginManager().registerEvents(this.listener, this);

        this.webHook = new WebHook(getConfig().getString("webHookUrl"));

        String msg = "\n" +
                "   _ _                   _ ___       \n" +
                " _| |_|___ ___ ___ ___ _| | | |_____ \n" +
                "| . | |_ -|  _| . |  _| . |_  |     |\n" +
                "|___|_|___|___|___|_| |___| |_|_|_|_|\n" +
                "                                     ";
        this.logger = Bukkit.getLogger();
        this.logger.info(msg);
    }

    @Override
    public void onDisable() {
        this.bot.stop();
    }

    public void onGuildMessage(String name, String msg) {
        Bukkit.broadcastMessage(getConfig().getString("messageFormat").replace("%n", name).replace("%m", msg));
    }

    public void onMinecraftChat(String name, String msg, String uuid) {
        this.webHook.sendWebHook(name, msg, uuid);
    }

}