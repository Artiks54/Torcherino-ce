package com.ariks.torcherino.GUI;

import com.ariks.torcherino.Config;
import com.ariks.torcherino.Tiles.TileCompresedTorch;
import com.ariks.torcherino.Tiles.TileTorch;
import com.ariks.torcherino.Tiles.TileTorcherinoBase;
import com.ariks.torcherino.Torcherino;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import java.io.IOException;

public class GuiConfiguration extends GuiScreen {
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
        fr.drawString("\u00A7n"+"CONFIGURATION TORCHERINO", textX + 65, textY, 0x55FF55);
        fr.drawString("NAME: "+"\u00A79"+tileName, textX, textY+=Step, 0xFF5555);
        fr.drawString("Currently working: "+"\u00A79"+torch.bolaenWork, textX, textY+=Step, 0xFF5555);
        fr.drawString("Spawn Particle: "+"\u00A79"+torch.boleanSpawnPrac,textX,textY+=Step,0xFF5555);
        fr.drawString("Step Count: "+"\u00A79"+torch.stepCount,textX,textY+=Step,0xFF5555);
        fr.drawString("Radius: "+"\u00A79"+torch.debugGetRadius() + "x" +"\u00A79"+ torch.debugGetRadius() + "x" + "\u00A79"+torch.debugGetRadius(), textX, textY += Step, 0xFF5555);
        if (lastClickedTileEntity instanceof TileTorch.TileBase1) {
            fr.drawString("Acceleration: " +"\u00A79"+ torch.debugGetSpeed() * Config.Torch_lvl1_S * 100 + "\u00A79"+"%", textX, textY += Step, 0xFF5555);
        }
        if (lastClickedTileEntity instanceof TileTorch.TileBase2) {
            fr.drawString("Acceleration: " +"\u00A79"+ torch.debugGetSpeed() * Config.Torch_lvl2_S * 100 + "\u00A79"+"%", textX, textY += Step, 0xFF5555);
        }
        if (lastClickedTileEntity instanceof TileTorch.TileBase3) {
            fr.drawString("Acceleration: " +"\u00A79"+ torch.debugGetSpeed() * Config.Torch_lvl3_S * 100 + "\u00A79"+"%", textX, textY += Step, 0xFF5555);
        }
        if (lastClickedTileEntity instanceof TileTorch.TileBase4) {
            fr.drawString("Acceleration: " +"\u00A79"+ torch.debugGetSpeed() * Config.Torch_lvl4_S * 100 + "\u00A79"+"%", textX, textY += Step, 0xFF5555);
        }
        if (lastClickedTileEntity instanceof TileTorch.TileBase5) {
            fr.drawString("Acceleration: " +"\u00A79"+ torch.debugGetSpeed() * Config.Torch_lvl5_S * 100 + "\u00A79"+"%", textX, textY += Step, 0xFF5555);
        }
        if (lastClickedTileEntity instanceof TileCompresedTorch.CompressedTileBase1) {
            fr.drawString("Acceleration: " +"\u00A79"+ torch.debugGetSpeed() * Config.CTorch_lvl1_S * 100 + "\u00A79"+"%", textX, textY += Step, 0xFF5555);
        }
        if (lastClickedTileEntity instanceof TileCompresedTorch.CompressedTileBase2) {
            fr.drawString("Acceleration: " +"\u00A79"+ torch.debugGetSpeed() * Config.CTorch_lvl2_S * 100 + "\u00A79"+"%", textX, textY += Step, 0x5555FF);
        }
        if (lastClickedTileEntity instanceof TileCompresedTorch.CompressedTileBase3) {
            fr.drawString("Acceleration: " +"\u00A79"+ torch.debugGetSpeed() * Config.CTorch_lvl3_S * 100 + "\u00A79"+"%", textX, textY += Step, 0xFF5555);
        }
        if (lastClickedTileEntity instanceof TileCompresedTorch.CompressedTileBase4) {
            fr.drawString("Acceleration: " +"\u00A79"+ torch.debugGetSpeed() * Config.CTorch_lvl4_S * 100 + "\u00A79"+"%", textX, textY += Step, 0xFF5555);
        }
        if (lastClickedTileEntity instanceof TileCompresedTorch.CompressedTileBase5) {
            fr.drawString("Acceleration: " +"\u00A79"+ torch.debugGetSpeed() * Config.CTorch_lvl5_S * 100 + "\u00A79"+"%", textX, textY += Step, 0xFF5555);
        }
        if(torch.modPrac == 0) {
            fr.drawString("\u00A7eParticle Flame", textX, textY+=Step, 0xFF5555);
        }
        if(torch.modPrac == 1) {
            fr.drawString("\u00A75Particle Dragon breath", textX, textY+=Step, 0xFF5555);
        }
        if(torch.modPrac == 2) {
            fr.drawString("\u00A74Particle Redstone", textX, textY+=Step, 0xFF5555);
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    @Override
    public void initGui() {
        super.initGui();
        buttonList.clear();
        int buttonWidth = 110; // Ширина кнопки
        int buttonHeight = 20; // Высота кнопки
        int x = (width - buttonWidth) / 2; // Рассчитываем координату x для кнопки
        int y = (height - buttonHeight) / 2; // Рассчитываем координату y для кнопки
        buttonList.add(new GuiButton(1,x-59,y+75,buttonWidth,buttonHeight,"Working"));
        buttonList.add(new GuiButton(2,x+59 ,y+75,buttonWidth,buttonHeight,"Particle Spawn"));
        buttonList.add(new GuiButton(3,x-59 ,y+105,buttonWidth,buttonHeight,"Change Area"));
        buttonList.add(new GuiButton(4,x+59 ,y+105,buttonWidth,buttonHeight,"Change Speed"));
        buttonList.add(new GuiButton(5,x-59 ,y+45,buttonWidth,buttonHeight,"Change step count"));
        buttonList.add(new GuiButton(6,x+59 ,y+45,buttonWidth,buttonHeight,"Change Prac"));
    }
    @Override
    protected void actionPerformed(GuiButton button) {
        if(button.id == 1){
            torch.toggleWork();
        }
        if(button.id == 2){
            torch.toggleSpawnPrac();
        }
        if(button.id == 3){
            torch.increaseArea();
        }
        if(button.id == 4){
            torch.increaseSpeed();
        }
        if(button.id == 5){
            torch.toggleStepCount();
        }
        if(button.id == 6){
            torch.toggleParticle();
        }
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
    public void updateScreen() {
        super.updateScreen();
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


