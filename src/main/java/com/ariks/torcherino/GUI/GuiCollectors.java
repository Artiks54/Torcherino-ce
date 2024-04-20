package com.ariks.torcherino.GUI;

import com.ariks.torcherino.Tiles.TileCollectors;
import com.ariks.torcherino.network.ModPacketHandler;
import com.ariks.torcherino.network.UpdateTilePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import java.awt.*;

public class GuiCollectors extends ExampleGui {
    private final TileCollectors tile;
    public GuiCollectors(TileCollectors tile, EntityPlayer player) {
        super(player,tile);
        this.tile = tile;
    }
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        drawModalRectWithCustomSizedTexture(textureX, textureY, 0, 0, 256, 128, 256, 128);
        fontRenderer.drawSplitString(LS.StrCollectorsGuiInfo, stringPositionX, stringPositionY, 230, Color.WHITE.getRGB());
        fontRenderer.drawSplitString(LS.TimeCollected+" : "+green+(tile.TimeCollect+" / "+tile.ConfigCollectorsMaxStorage), stringPositionX, stringPositionY+20, 230, Color.WHITE.getRGB());
    super.drawScreen(mouseX, mouseY, partialTicks);
    }
    @Override
    public void initGui() {
        super.initGui();
        ModPacketHandler.network.sendToServer(new UpdateTilePacket(this.tile.getPos(), 1));
    }
    @Override
    public void onGuiClosed() {
        ModPacketHandler.network.sendToServer(new UpdateTilePacket(this.tile.getPos(), 2));
    }
}
