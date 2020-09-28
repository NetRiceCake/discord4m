package com.github.netricecake.discord4m.discord;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageHandler extends ListenerAdapter {

    private DiscordBot bot;

    public MessageHandler(DiscordBot bot) {
        this.bot = bot;
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        this.bot.onMessage(event);
    }

}
