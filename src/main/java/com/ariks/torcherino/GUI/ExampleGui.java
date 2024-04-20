package com.ariks.torcherino.GUI;

import com.ariks.torcherino.Torcherino;
import com.ariks.torcherino.util.LocalizedStringKey;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.input.Keyboard;

public abstract class ExampleGui extends GuiScreen {
    public final ResourceLocation texture = new ResourceLocation(Torcherino.MOD_ID, "textures/gui/gui_small.png");
    public final TextFormatting green = TextFormatting.GREEN;
    private final EntityPlayer player;
    private final BlockPos initialPosition;
    private final TileEntity tile;
    public static int stringPositionX,stringPositionY,textureX,textureY,screenWidthPixels,screenHeightPixels;
    LocalizedStringKey LS = new LocalizedStringKey();
    public ExampleGui(EntityPlayer player, TileEntity tile) {
        this.player = player;
        this.initialPosition = player.getPosition();
        this.tile = tile;
    }
    @Override
    public void initGui() {
        super.initGui();
    }
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        screenWidthPixels = width;
        screenHeightPixels = height;
        textureX = (screenWidthPixels - 256) / 2;
        textureY = (screenHeightPixels - 128) / 2;
        stringPositionX = textureX + 15;
        stringPositionY = textureY + 15;
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    @Override
    protected void keyTyped(char typedChar, int keyCode) {
        if (keyCode == Keyboard.KEY_E || keyCode == Keyboard.KEY_ESCAPE) {
            player.closeScreen();
        }
    }
    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
    @Override
    public void updateScreen() {
        super.updateScreen();
        this.initGui();
        if (player != null && player.getDistanceSq(initialPosition) > 8.0) {
            player.closeScreen();
        }
        if(tile.isInvalid()){
            assert player != null;
            player.closeScreen();
        }
    }
}
