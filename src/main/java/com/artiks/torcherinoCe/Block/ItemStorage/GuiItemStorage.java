package com.artiks.torcherinoCe.Block.ItemStorage;

import com.artiks.torcherinoCe.Block.Core.ExampleGuiContainer;
import com.artiks.torcherinoCe.utility.LocalizedStringKey;
import com.artiks.torcherinoCe.utility.NumberFormats;
import net.minecraft.entity.player.InventoryPlayer;
import java.awt.*;

public class GuiItemStorage extends ExampleGuiContainer {
    private final TileItemStorage tile;
    public GuiItemStorage(InventoryPlayer inventory, TileItemStorage tileEntity) {
        super(new ContainerItemStorage(inventory, tileEntity));
        this.tile = tileEntity;
        setTexture("textures/gui/gui_item_storage.png", 175, 167);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        fontRenderer.drawString(LocalizedStringKey.StrTextInfo,10,10,Color.WHITE.getRGB());
        fontRenderer.drawString(LocalizedStringKey.StrStored + " " + NumberFormats.formatNumber(tile.getItemStored()),10,50,Color.WHITE.getRGB());
        fontRenderer.drawString(LocalizedStringKey.StrMaxStorage + " " + NumberFormats.formatNumber(tile.getItemStored()),10,30, Color.WHITE.getRGB());
    }
}