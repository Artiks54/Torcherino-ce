package com.artiks.torcherinoCe.utility;

import com.artiks.torcherinoCe.Tags;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import static com.artiks.torcherinoCe.utility.Config.BooleanHelloMsg;

public class JoinDiscord {
    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (BooleanHelloMsg) {
            TextComponentString hello = new TextComponentString("Hello " + event.player.getName());
            hello.getStyle().setColor(TextFormatting.GOLD);
            event.player.sendMessage(hello);
            TextComponentString mainMessage = new TextComponentString("Torcherino-ce version. " + TextFormatting.LIGHT_PURPLE + Tags.VERSION);
            mainMessage.getStyle().setColor(TextFormatting.GOLD);
            event.player.sendMessage(mainMessage);
            TextComponentString linkMessage = new TextComponentString(TextFormatting.GOLD + "->" + TextFormatting.LIGHT_PURPLE + " https://discord.gg/Mp5sEpE3B3 " + TextFormatting.GREEN + "<-");
            linkMessage.getStyle()
                    .setUnderlined(true)
                    .setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/Mp5sEpE3B3"))
                    .setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentString("Join the official discord link mod Torcherino CE")));
            event.player.sendMessage(linkMessage);
        }
    }
}
