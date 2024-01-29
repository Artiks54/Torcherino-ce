package com.ariks.torcherino.GUI;
import com.ariks.torcherino.Config;
import com.ariks.torcherino.Tiles.TileCompresedTorch;
import com.ariks.torcherino.Tiles.TileTorch;
import com.ariks.torcherino.Tiles.TileTorcherinoBase;
import com.ariks.torcherino.Torcherino;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import java.io.IOException;

public class GuiTest extends GuiScreen {
    public static TileEntity lastClickedTileEntity;
    public static String tileName;
    TileTorcherinoBase torch = (TileTorcherinoBase) lastClickedTileEntity;
    public int TextureWidth = 256;
    public int TextureHeight = 256;
    private static final ResourceLocation BACKGROUND = new ResourceLocation(Torcherino.MOD_ID, "textures/gui/gui.png");
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        int screenWidth = this.width;
        int screenHeight = this.height;
        int TextureX = (screenWidth - TextureWidth) / 2;
        int TextureY = (screenHeight - TextureHeight) / 2;
        mc.getTextureManager().bindTexture(BACKGROUND);
        drawModalRectWithCustomSizedTexture(TextureX, TextureY, 0, 0, TextureWidth, TextureHeight, TextureWidth, TextureHeight);
        int textX = TextureX + 12;
        int textY = TextureY + 12;
        int Step = 12;
        FontRenderer fr = fontRenderer;
        if (lastClickedTileEntity instanceof TileTorcherinoBase) {
            fr.drawString("\u00A7nINFORMATION ABOUT TORCHERINO", textX + 35, textY, 0x55FF55);
            fr.drawString("NAME: " + tileName, textX, textY += Step, 0xFFFFFF);
            fr.drawString("1)Currently working: " + torch.bolaenWork, textX, textY += Step, 0xFF5555);
            fr.drawString("2)Position cord: " + " X: " + torch.getPos().getX() + " Y: " + torch.getPos().getY() + " Z: " + torch.getPos().getZ(), textX, textY += Step, 0xFF5555);
            fr.drawString("3)Is there a spawn particle: " + torch.boleanSpawnPrac, textX, textY += Step, 0xFF5555);
            fr.drawString("4)The number of ticks at the moment: " + torch.debugGetCount(), textX, textY += Step, 0xFF5555);
            fr.drawString("5)Radius: " + torch.debugGetRadius() + "x" + torch.debugGetRadius() + "x" + torch.debugGetRadius(), textX, textY += Step, 0xFF5555);
            fr.drawString("\u00A7nCONFIG FILE CFG", textX + 70, textY += Step, 0x55FF55);
            fr.drawString("7)The required number of ticks for the", textX, textY += Step, 0xFFAA00);
            fr.drawString("appearance of particles: " + Config.tickCount, textX, textY += Step, 0xFFAA00);
            fr.drawString("8)The step spawn of particles: " + torch.stepCount, textX, textY += Step, 0xFFAA00);
            fr.drawString("9)Particle appearance mode: " + Config.PracTicleSpawn, textX, textY += Step, 0xFFAA00);
            fr.drawString("\u00A7nSPECIAL INFORMATION", textX + 65, textY += Step, 0x55FF55);
            fr.drawString("10)Selected speed mode: " + torch.debugGetSpeed(), textX, textY += Step, 0x5555FF);
        }
        if (lastClickedTileEntity instanceof TileTorch.TileBase1) {
            fr.drawString("12)Acceleration: " + torch.debugGetSpeed() * Config.Torch_lvl1_S * 100 + "%", textX, textY += Step, 0x5555FF);
            fr.drawString("13)Total speed modes: " + Config.Torch_lvl1_M, textX, textY += Step, 0x5555FF);
            fr.drawString("14)Maximum working radius: " + Config.Torch_lvl1_R, textX, textY += Step, 0x5555FF);
        }
        if (lastClickedTileEntity instanceof TileTorch.TileBase2) {
            fr.drawString("12)Acceleration: " + torch.debugGetSpeed() * Config.Torch_lvl2_S * 100 + "%", textX, textY += Step, 0x5555FF);
            fr.drawString("13)Total speed modes: " + Config.Torch_lvl2_M, textX, textY += Step, 0x5555FF);
            fr.drawString("14)Maximum working radius: " + Config.Torch_lvl2_R, textX, textY += Step, 0x5555FF);
        }
        if (lastClickedTileEntity instanceof TileTorch.TileBase3) {
            fr.drawString("12)Acceleration: " + torch.debugGetSpeed() * Config.Torch_lvl3_S * 100 + "%", textX, textY += Step, 0x5555FF);
            fr.drawString("13)Total speed modes: " + Config.Torch_lvl3_M, textX, textY += Step, 0x5555FF);
            fr.drawString("14)Maximum working radius: " + Config.Torch_lvl3_R, textX, textY += Step, 0x5555FF);
        }
        if (lastClickedTileEntity instanceof TileTorch.TileBase4) {
            fr.drawString("12)Acceleration: " + torch.debugGetSpeed() * Config.Torch_lvl4_S * 100 + "%", textX, textY += Step, 0x5555FF);
            fr.drawString("13)Total speed modes: " + Config.Torch_lvl4_M, textX, textY += Step, 0x5555FF);
            fr.drawString("14)Maximum working radius: " + Config.Torch_lvl4_R, textX, textY += Step, 0x5555FF);
        }
        if (lastClickedTileEntity instanceof TileTorch.TileBase5) {
            fr.drawString("12)Acceleration: " + torch.debugGetSpeed() * Config.Torch_lvl5_S * 100 + "%", textX, textY += Step, 0x5555FF);
            fr.drawString("13)Total speed modes: " + Config.Torch_lvl5_M, textX, textY += Step, 0x5555FF);
            fr.drawString("14)Maximum working radius: " + Config.Torch_lvl5_R, textX, textY += Step, 0x5555FF);
        }
        if (lastClickedTileEntity instanceof TileCompresedTorch.CompressedTileBase1) {
            fr.drawString("12)Acceleration: " + torch.debugGetSpeed() * Config.CTorch_lvl1_S * 100 + "%", textX, textY += Step, 0x5555FF);
            fr.drawString("13)Total speed modes: " + Config.CTorch_lvl1_M, textX, textY += Step, 0x5555FF);
            fr.drawString("14)Maximum working radius: " + Config.CTorch_lvl1_R, textX, textY += Step, 0x5555FF);
        }
        if (lastClickedTileEntity instanceof TileCompresedTorch.CompressedTileBase2) {
            fr.drawString("12)Acceleration: " + torch.debugGetSpeed() * Config.CTorch_lvl2_S * 100 + "%", textX, textY += Step, 0x5555FF);
            fr.drawString("13)Total speed modes: " + Config.CTorch_lvl2_M, textX, textY += Step, 0x5555FF);
            fr.drawString("14)Maximum working radius: " + Config.CTorch_lvl2_R, textX, textY += Step, 0x5555FF);
        }
        if (lastClickedTileEntity instanceof TileCompresedTorch.CompressedTileBase3) {
            fr.drawString("12)Acceleration: " + torch.debugGetSpeed() * Config.CTorch_lvl3_S * 100 + "%", textX, textY += Step, 0x5555FF);
            fr.drawString("13)Total speed modes: " + Config.CTorch_lvl3_M, textX, textY += Step, 0x5555FF);
            fr.drawString("14)Maximum working radius: " + Config.CTorch_lvl3_R, textX, textY += Step, 0x5555FF);
        }
        if (lastClickedTileEntity instanceof TileCompresedTorch.CompressedTileBase4) {
            fr.drawString("12)Acceleration: " + torch.debugGetSpeed() * Config.CTorch_lvl4_S * 100 + "%", textX, textY += Step, 0x5555FF);
            fr.drawString("13)Total speed modes: " + Config.CTorch_lvl4_M, textX, textY += Step, 0x5555FF);
            fr.drawString("14)Maximum working radius: " + Config.CTorch_lvl4_R, textX, textY += Step, 0x5555FF);
        }
        if (lastClickedTileEntity instanceof TileCompresedTorch.CompressedTileBase5) {
            fr.drawString("12)Acceleration: " + torch.debugGetSpeed() * Config.CTorch_lvl5_S * 100 + "%", textX, textY += Step, 0x5555FF);
            fr.drawString("13)Total speed modes: " + Config.CTorch_lvl5_M, textX, textY += Step, 0x5555FF);
            fr.drawString("14)Maximum working radius: " + Config.CTorch_lvl5_R, textX, textY += Step, 0x5555FF);
        }
    }
    @Override
    public void updateScreen() {
        super.updateScreen();
    }
    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        Minecraft mine = Minecraft.getMinecraft();
        super.keyTyped(typedChar, keyCode);
        if (keyCode == Keyboard.KEY_E) {
            this.mc.displayGuiScreen(null);
        }
    }
    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
    }
    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}