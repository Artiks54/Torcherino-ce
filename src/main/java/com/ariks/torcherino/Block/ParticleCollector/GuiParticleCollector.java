package com.ariks.torcherino.Block.ParticleCollector;

import com.ariks.torcherino.Block.ExampleGuiContainer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import java.awt.*;

public class GuiParticleCollector extends ExampleGuiContainer {
    private final TileParticleCollector tile;
    public GuiParticleCollector(InventoryPlayer inventory, TileParticleCollector tileEntity, EntityPlayer player) {
        super(new ContainerParticleCollector(inventory, tileEntity, player));
        this.tile = tileEntity;
        SetTexture("textures/gui/gui3.png");
        SetWidth(176);
        SetHeight(168);
        setBooleanBar(true);
        SetBarSettings(42, 42, 96, 18, 1, 170);
    }
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        int level = tile.getValue(5);
        int gen = tile.getValue(3);
        int nup = tile.getValue(4);
        FontRenderer GenUp = this.fontRenderer;
        FontRenderer Level = this.fontRenderer;
        if (level < 5) {
            drawTexturedModalRect(x, y+36, 1, 216, 84,22);
            GenUp.drawString(gen+" / "+nup,x+9,y+40,Color.WHITE.getRGB());
            Level.drawString("Level: "+level,x+9,y+62, Color.WHITE.getRGB());
            GenUp.setUnicodeFlag(false);
            Level.setUnicodeFlag(false);
        } else {
            Level.drawString("Level maximum",x+8,y+62, Color.WHITE.getRGB());
        }
    }
    @Override
    public void UpdateBar() {
        SetBarValue(tile.getValue(1),tile.getValue(2));
    }
}