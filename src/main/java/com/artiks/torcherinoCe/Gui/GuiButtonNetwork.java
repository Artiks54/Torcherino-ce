package com.artiks.torcherinoCe.Gui;

import com.artiks.torcherinoCe.Block.Core.TileExampleContainer;
import com.artiks.torcherinoCe.network.RegistryNetwork;
import com.artiks.torcherinoCe.network.Packet.UpdateTilePacketButton;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.config.GuiButtonExt;
import org.jetbrains.annotations.NotNull;

public class GuiButtonNetwork extends GuiButtonExt {
    private final int valueChange;
    private final TileExampleContainer tile;

    public GuiButtonNetwork(TileExampleContainer tile, int id, int xPos, int yPos, int width, int height, String displayString, int value) {
        super(id, xPos, yPos, width, height, displayString);
        this.valueChange = value;
        this.tile = tile;
    }
    @Override
    public boolean mousePressed(@NotNull Minecraft mc, int mouseX, int mouseY) {
        if (super.mousePressed(mc, mouseX, mouseY)) {
            RegistryNetwork.network.sendToServer(new UpdateTilePacketButton(tile.getPos(),valueChange));
            return true;
        } else {
            return false;
        }
    }
}