package com.ariks.torcherino.Block.ParticleCollector;

import com.ariks.torcherino.Torcherino;
import com.ariks.torcherino.util.LocalizedStringKey;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiParticleCollector extends GuiContainer {
    private final TileParticleCollector tile;
    public final LocalizedStringKey LS = new LocalizedStringKey();
    private final ResourceLocation texture = new ResourceLocation(Torcherino.MOD_ID, "textures/gui/gui1.png");
    public GuiParticleCollector(InventoryPlayer inventory, TileParticleCollector tileEntity, EntityPlayer player) {
        super(new ContainerParticleCollector(inventory,tileEntity,player));
        this.tile = tileEntity;
    }
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX,mouseY);
    }
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(texture);
        int x = (this.width - xSize) / 2;
        int y = (this.height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize,ySize);
        int progress = tile.getField(1);
        int maxProgress = tile.getField(2);
        int fix = 1;
        int wightTexture = 34;
        int heightTexture = 34;
        //Left to right
        int StartX_left_to_right = 35;
        int StartY_left_to_right = 22;
        //Right to left
        int StartX_right_to_left = 140+fix;
        int StartY_right_to_left = 22;
        //draw
        double draw = (double) (progress * (wightTexture + 1)) / (maxProgress + 1);
        //Left to right
        drawTexturedModalRect(x + StartX_left_to_right, y + StartY_left_to_right, 1, 170,(int) draw, heightTexture);
        //Right to left
        drawTexturedModalRect(x + StartX_right_to_left - (int) draw, y + StartY_right_to_left,(70+fix) - (int) draw , 170,(int) draw, heightTexture);
    }
}