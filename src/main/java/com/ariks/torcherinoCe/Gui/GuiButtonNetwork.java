package com.ariks.torcherinoCe.Gui;

import com.ariks.torcherinoCe.Block.Core.TileExampleContainer;
import com.ariks.torcherinoCe.Register.RegistryNetwork;
import com.ariks.torcherinoCe.network.UpdateTilePacket;
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
            RegistryNetwork.network.sendToServer(new UpdateTilePacket(tile.getPos(),valueChange));
            return true;
        } else {
            return false;
        }
    }
}