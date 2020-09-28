package com.github.netricecake.discord4m.discord;

import com.github.netricecake.discord4m.Main;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.bukkit.Bukkit;

import javax.security.auth.login.LoginException;

public class DiscordBot {

    private Main main;

    private JDA jda;

    public DiscordBot(Main main) {
        this.main = main;
    }

    public void start() {
        try {
            this.jda = JDABuilder.createDefault(this.main.getConfig().getString("token"))
                    .addEventListeners(new MessageHandler(this))
                    .build();
        } catch (LoginException e) {
            Bukkit.getLogger().warning(e.getMessage());
        }
    }

    public void stop() {
        this.jda.shutdownNow();
    }

    public void onMessage(GuildMessageReceivedEvent event) {
        if(this.main.getConfig().getLong("guildId") == event.getGuild().getIdLong() && this.main.getConfig().getLong("channelId") == event.getChannel().getIdLong()) {
            this.main.onGuildMessage(event.getMember().getNickname() == null ? event.getMember().getUser().getName() : event.getMember().getNickname(), event.getMessage().getContentRaw());
        }
    }

}
