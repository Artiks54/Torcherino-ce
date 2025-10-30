package com.artiks.torcherinoCe.Gui;

import com.artiks.torcherinoCe.Block.Core.TileExampleContainer;
import com.artiks.torcherinoCe.network.RegistryNetwork;
import com.artiks.torcherinoCe.torcherinoCe;
import com.artiks.torcherinoCe.network.Packet.UpdateTilePacketButton;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.config.GuiButtonExt;
import org.jetbrains.annotations.NotNull;

public class GuiItemButton extends GuiButtonExt {
    private ItemStack stack = ItemStack.EMPTY;
    private final int valueChange;
    private final TileExampleContainer tile;
    public GuiItemButton(TileExampleContainer tile,int buttonId, int x, int y,int value) {
        super(buttonId, x, y, 20, 20, "");
        this.tile = tile;
        this.valueChange = value;
    }
    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float p) {
        if (this.visible) {
            super.drawButton(mc, mouseX, mouseY, p);
            torcherinoCe.proxy.renderItemOnScreen(this.stack, this.x+2, this.y+2);
            }
        }
    @Override
    public boolean mousePressed(@NotNull Minecraft mc, int mouseX, int mouseY) {
        if (super.mousePressed(mc, mouseX, mouseY)) {
            if (valueChange != 0) {
                RegistryNetwork.network.sendToServer(new UpdateTilePacketButton(tile.getPos(), valueChange));
                return true;
            }
        }
        return false;
    }
    public void setStackRender(ItemStack stackRender) {
        this.stack = stackRender;
    }
}