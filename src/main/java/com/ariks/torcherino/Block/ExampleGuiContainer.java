package com.ariks.torcherino.Block;

import com.ariks.torcherino.Torcherino;
import com.ariks.torcherino.util.LocalizedStringKey;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import java.awt.*;

@SideOnly(Side.CLIENT)
public abstract class ExampleGuiContainer extends GuiContainer {
    private final ResourceLocation texture = new ResourceLocation(Torcherino.MOD_ID, "textures/gui/gui_small.png");
    private final TileEntity tile;
    public final LocalizedStringKey LS = new LocalizedStringKey();
    public ExampleGuiContainer(Container container,TileEntity tile) {
        super(container);
        this.xSize = 256;
        this.ySize = 128;
        this.tile = tile;
    }
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX,mouseY);
        this.drawName();
    }
    private void drawName(){
        int x = (this.width - xSize) / 2;
        int y = (this.height - ySize) / 2;
        this.fontRenderer.drawString(tile.getBlockType().getLocalizedName(), x+10,y+10, Color.WHITE.getRGB());
    }
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        this.drawDefaultBackground();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(texture);
        int x = (this.width - xSize) / 2;
        int y = (this.height - ySize) / 2;
        drawModalRectWithCustomSizedTexture(x, y, 0, 0, xSize,ySize,xSize,ySize);
    }
    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
