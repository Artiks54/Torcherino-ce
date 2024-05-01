package com.ariks.torcherino.Block.Aceleration;

import com.ariks.torcherino.Block.ExampleGuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import java.awt.*;
import java.text.DecimalFormat;

@SideOnly(Side.CLIENT)
public class GuiAceleration extends ExampleGuiContainer {
    private final TileAcceleration tile;

    public GuiAceleration(InventoryPlayer inventory, TileAcceleration tileEntity, EntityPlayer player) {
        super(new ContainerAceleration(inventory, tileEntity,player));
        this.tile = tileEntity;
    }
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        int posX = (this.xSize / 2) -121;
        int timeStorage = tile.getValue(1);
        this.fontRenderer.drawString(tile.getBlockType().getLocalizedName(), posX+4,10, Color.ORANGE.getRGB());
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedTimeStorage = decimalFormat.format(timeStorage);
        this.fontRenderer.drawString(LS.TimeCollected+" "+formattedTimeStorage,posX+4,25, Color.WHITE.getRGB());
    }
}