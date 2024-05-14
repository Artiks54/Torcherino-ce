package com.ariks.torcherino.util;

import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class JoinDiscord {
    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        TextComponentString mainMessage = new TextComponentString("Hello, join the official Torcherino-ce discord: ");
        mainMessage.getStyle().setColor(TextFormatting.GREEN);
        TextComponentString linkMessage = new TextComponentString("[https://discord.gg/Mp5sEpE3B3]");
        linkMessage.getStyle()
                .setColor(TextFormatting.BLUE)
                .setUnderlined(true)
                .setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/Mp5sEpE3B3"))
                .setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentString("Click to open the link")));
        mainMessage.appendSibling(linkMessage);
        event.player.sendMessage(mainMessage);
    }
}
