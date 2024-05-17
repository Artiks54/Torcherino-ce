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
        super(new ContainerAceleration(inventory, tileEntity,player),tileEntity);
        this.tile = tileEntity;
    }
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        int x = (this.width - xSize) / 2;
        int y = (this.height - ySize) / 2;
        int timeStorage = tile.getValue(1);
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedTimeStorage = decimalFormat.format(timeStorage);
        this.fontRenderer.drawString(LS.TimeCollected+" "+formattedTimeStorage,x+10,y+25, Color.WHITE.getRGB());
    }
}