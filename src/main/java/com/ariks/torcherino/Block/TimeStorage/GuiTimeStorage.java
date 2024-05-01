package com.ariks.torcherino.Block.TimeStorage;

import com.ariks.torcherino.Block.ExampleGuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import java.awt.*;
import java.text.DecimalFormat;

@SideOnly(Side.CLIENT)
public class GuiTimeStorage extends ExampleGuiContainer {
    private final TileTimeStorage tile;
    int timeStorage,timeStorageMax;
    public GuiTimeStorage(InventoryPlayer inventory, TileTimeStorage tileEntity, EntityPlayer player) {
        super(new ContainerTimeStorage(inventory, tileEntity,player));
        this.tile = tileEntity;
    }
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        int posX = (this.xSize / 2) -121;
        this.timeStorage = tile.getValue(1);
        this.timeStorageMax = tile.getValue(2);
        this.fontRenderer.drawString(tile.getBlockType().getLocalizedName(), posX+4,10, Color.ORANGE.getRGB());
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedTimeStorageMax = decimalFormat.format(timeStorageMax);
        String formattedTimeStorage = decimalFormat.format(timeStorage);
        this.fontRenderer.drawString(LS.TimeCollected+" "+formattedTimeStorage +" / "+ formattedTimeStorageMax,posX+4,25, Color.WHITE.getRGB());
    }
}