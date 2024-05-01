package com.ariks.torcherino.Block.ParticleCollector;

import com.ariks.torcherino.Torcherino;
import com.ariks.torcherino.util.LocalizedStringKey;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import java.awt.*;

public class GuiParticleCollector extends GuiContainer {
    private final TileParticleCollector tile;
    public final LocalizedStringKey LS = new LocalizedStringKey();
    private final ResourceLocation texture = new ResourceLocation(Torcherino.MOD_ID, "textures/gui/gui.png");
    public GuiParticleCollector(InventoryPlayer inventory, TileParticleCollector tileEntity, EntityPlayer player) {
        super(new ContainerParticleCollector(inventory,tileEntity,player));
        this.tile = tileEntity;
    }
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX,mouseY);
    }
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        this.drawDefaultBackground();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(texture);
        int x = (this.width - xSize) / 2;
        int y = (this.height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize,ySize);
    }
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        int progress = tile.getField(1);
        int MaxProgress = tile.getField(2);
        int percent = (progress * 100) / MaxProgress;
        int x = (xSize) / 2;
        int y = (ySize) / 2;
        this.fontRenderer.drawString(tile.getBlockType().getLocalizedName(),x-80,y-75,Color.WHITE.getRGB());
        this.fontRenderer.drawString(LS.StrTextProgress+" "+percent+"%",x-80,y-60,Color.WHITE.getRGB());
    }
}