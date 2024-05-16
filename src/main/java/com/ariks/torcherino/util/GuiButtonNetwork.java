package com.ariks.torcherino.util;

import com.ariks.torcherino.Block.TileExampleContainer;
import com.ariks.torcherino.Register.RegistryNetwork;
import com.ariks.torcherino.network.UpdateTilePacket;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.config.GuiButtonExt;

public class GuiButtonNetwork extends GuiButtonExt {
    private final int valueChange;
    private final TileExampleContainer tile;

    public GuiButtonNetwork(TileExampleContainer tile, int id, int xPos, int yPos, int width, int height, String displayString, int value) {
        super(id, xPos, yPos, width, height, displayString);
        this.valueChange = value;
        this.tile = tile;
    }
    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        if (super.mousePressed(mc, mouseX, mouseY)) {
            RegistryNetwork.network.sendToServer(new UpdateTilePacket(tile.getPos(),valueChange));
            return true;
        } else {
            return false;
        }
    }
}