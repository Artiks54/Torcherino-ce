package com.ariks.torcherino.Block.Torcherino;

import com.ariks.torcherino.Block.ExampleGuiContainer;
import com.ariks.torcherino.util.*;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import java.awt.*;

@SideOnly(Side.CLIENT)
public class GuiTorcherino extends ExampleGuiContainer {
    private final TileTorcherinoBase tile;
    private GuiSliderInt sliderRadius,sliderSpeed,sliderR,sliderG,sliderB;
    private GuiButton SettingsOpen,SettingsClosed;
    private GuiButtonNetwork buttonWork,buttonRender;
    private GuiItemButton buttonInfo;
    GuiColorCube colorCube = new GuiColorCube();
    private boolean drawCube;

    public GuiTorcherino(InventoryPlayer inventory, TileTorcherinoBase tileEntity, EntityPlayer player) {
        super(new ContainerTorcherino(inventory, tileEntity, player));
        this.tile = tileEntity;
    }
    @Override
    public void initGui() {
        super.initGui();
        this.DelCube();
        final int maxColor = 255;
        final int min = 0;
        int x = (this.width - xSize) / 2;
        int y = (this.height - ySize) / 2;
        buttonList.clear();
        sliderRadius = new GuiSliderInt(tile, 11, x+10, y+20, 235, 20, min, tile.getValue(5), 1,LS.StrTextRadius,true);
        sliderSpeed = new GuiSliderInt(tile, 12, x+10, y+45, 235, 20, min, tile.getValue(6), 2,LS.StrTextSpeed,true);
        sliderR = new GuiSliderInt(tile, 13, x+10, y+20, 160, 20, min, maxColor, 8,"Red",true);
        sliderG = new GuiSliderInt(tile, 14, x+10, y+45, 160, 20, min, maxColor, 9,"Green",true);
        sliderB = new GuiSliderInt(tile, 15, x+10, y+70, 160, 20, min, maxColor, 10,"Blued",true);
        buttonWork = new GuiButtonNetwork(tile,1, x+10, y+70, 235, 20, "",1);
        buttonRender = new GuiButtonNetwork(tile,2, x+45, y+95, 200, 20, "",2);
        SettingsOpen = new net.minecraft.client.gui.GuiButton(3, x+35, y+95, 210, 20, "");
        buttonInfo = new GuiItemButton(5, x+10, y+95);
        buttonInfo.setStackRender(new ItemStack(Items.PAPER));
        SettingsClosed = new net.minecraft.client.gui.GuiButton(4,x+10,y+95,30,20,"<-");
        this.ConfigRenderUpdate();
        buttonList.add(buttonInfo);
        buttonList.add(buttonWork);
        buttonList.add(sliderRadius);
        buttonList.add(sliderSpeed);
        this.offStrings();
    }
    private void ConfigRenderUpdate(){
        if(Config.BooleanRender){
            buttonList.add(buttonRender);
            buttonList.add(SettingsOpen);
            buttonList.add(SettingsClosed);
            buttonList.add(sliderR);
            buttonList.add(sliderG);
            buttonList.add(sliderB);
        }else{
            buttonList.remove(buttonRender);
            buttonList.remove(SettingsOpen);
            buttonList.remove(SettingsClosed);
            buttonList.remove(sliderR);
            buttonList.remove(sliderG);
            buttonList.remove(sliderB);
        }
    }
    private void setButtonStatus(net.minecraft.client.gui.GuiButton[] buttons, boolean enabled, boolean visible) {
        for (net.minecraft.client.gui.GuiButton button : buttons) {
            button.enabled = enabled;
            button.visible = visible;
        }
    }
    private void offMain() {
        setButtonStatus(new net.minecraft.client.gui.GuiButton[] {buttonWork, sliderRadius, sliderSpeed, SettingsOpen,buttonInfo}, false, false);
        setButtonStatus(new net.minecraft.client.gui.GuiButton[] {buttonRender, sliderR, sliderG, sliderB, SettingsClosed}, true, true);
    }
    private void offStrings() {
        setButtonStatus(new net.minecraft.client.gui.GuiButton[] {sliderR, sliderG, sliderB, buttonRender, SettingsClosed}, false, false);
        setButtonStatus(new net.minecraft.client.gui.GuiButton[] {buttonWork, sliderRadius, sliderSpeed, SettingsOpen,buttonInfo}, true, true);
    }
    private void AddCube(){
        if(drawCube) {
            int x = (this.width - xSize) / 2;
            int y = (this.height - ySize) / 2;
            float red = sliderR.getSliderValue() / 255f;
            float green = sliderG.getSliderValue() / 255f;
            float blue = sliderB.getSliderValue() / 255f;
            colorCube.setCube(70, x+210, y+55, red, green, blue);
            colorCube.drawCube();
        }
    }
    private void DelCube(){
        drawCube = false;
        colorCube.clearCube();
    }
    @Override
    protected void actionPerformed(net.minecraft.client.gui.GuiButton button) {
        if(button.id == 3){
            this.offMain();
            drawCube = true;
        }
        if(button.id == 4){
            this.offStrings();
            this.DelCube();
        }
    }
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.updateButton();
        if(Config.BooleanRender) {
            this.AddCube();
        }
        for (net.minecraft.client.gui.GuiButton button : buttonList) {
            if (button.isMouseOver()) {
                if (button == sliderRadius) {
                    int radius = (int) sliderRadius.getSliderValue();
                    drawHoveringText(radius+"x"+radius+"x"+radius, mouseX, mouseY);
                } else if (button == sliderSpeed) {
                    drawHoveringText(tile.getValue(7) * 100 * sliderSpeed.getSliderValue() + "%", mouseX, mouseY);
                } else if (button == sliderR) {
                    drawHoveringText(String.valueOf(sliderR.getSliderValue()), mouseX, mouseY);
                } else if (button == sliderG) {
                    drawHoveringText(String.valueOf(sliderG.getSliderValue()), mouseX, mouseY);
                } else if (button == sliderB) {
                    drawHoveringText(String.valueOf(sliderB.getSliderValue()), mouseX, mouseY);
                } else if (button == buttonInfo) {
                    drawHoveringText(TextFormatting.GREEN+"Info",mouseX,mouseY-16);
                    drawHoveringText("Pos: " + "X: " + tile.getPos().getX() + " Y: " + tile.getPos().getY() + " Z: " + tile.getPos().getZ(), mouseX,mouseY);
                    drawHoveringText("Max " + LS.StrTextRadius + ": " + tile.getValue(5), mouseX, mouseY+16);
                    drawHoveringText(LS.StrTextSpeed + ": " + tile.getValue(7) * 100 + "%", mouseX, mouseY + 32);
                }
            }
        }
    }
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        int posX = (this.xSize / 2) -121;
        this.fontRenderer.drawString(tile.getBlockType().getLocalizedName(), posX+5,10, Color.WHITE.getRGB());
    }
    public void updateButton() {
        int renderGet = tile.getValue(4);
        int workGet = tile.getValue(3);
        SettingsOpen.displayString = "[ "+LS.StrTextRenderOff+" ]";
        switch (workGet) {
            case 0: buttonWork.displayString = LS.StrTextWorking;break;
            case 1: buttonWork.displayString = LS.StrTextAlways;break;
            case 2: buttonWork.displayString = LS.StrRedstoneMode;break;
            case 3: buttonWork.displayString = LS.StrRedstoneModeRevers;break;
        }
        switch (renderGet) {
            case 0: buttonRender.displayString = LS.StrTextRenderNull;break;
            case 1: buttonRender.displayString = LS.StrTextRenderLine;break;
            case 2: buttonRender.displayString = LS.StrTextRenderBox;break;
            case 3: buttonRender.displayString = LS.StrTextRenderComb;break;
        }
    }
}