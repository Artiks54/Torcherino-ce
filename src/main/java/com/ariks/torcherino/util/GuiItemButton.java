package com.ariks.torcherino.util;

import com.ariks.torcherino.Block.TileExampleContainer;
import com.ariks.torcherino.Register.RegistryNetwork;
import com.ariks.torcherino.Torcherino;
import com.ariks.torcherino.network.UpdateTilePacket;
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
                Torcherino.proxy.renderItemOnScreen(this.stack, this.x+2, this.y+2);
            }
        }
    @Override
    public boolean mousePressed(@NotNull Minecraft mc, int mouseX, int mouseY) {
        if (super.mousePressed(mc, mouseX, mouseY)) {
            if (valueChange != 0) {
                RegistryNetwork.network.sendToServer(new UpdateTilePacket(tile.getPos(), valueChange));
                return true;
            }
        }
        return false;
    }
    public void setStackRender(ItemStack stackRender) {
        this.stack = stackRender;
    }
}