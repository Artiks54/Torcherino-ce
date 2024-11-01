package com.ariks.torcherino.Block.Torcherino;

import com.ariks.torcherino.Gui.GuiButtonNetwork;
import com.ariks.torcherino.Gui.GuiColorCube;
import com.ariks.torcherino.Gui.GuiItemButton;
import com.ariks.torcherino.Gui.GuiSliderInt;
import com.ariks.torcherino.Torcherino;
import com.ariks.torcherino.util.*;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import java.awt.*;

@SideOnly(Side.CLIENT)
public class GuiTorcherino extends GuiContainer {
    private final TileTorcherinoBase tile;
    private final LocalizedStringKey LS = new LocalizedStringKey();
    private final ResourceLocation texture = new ResourceLocation(Torcherino.MOD_ID, "textures/gui/gui_torch.png");
    private GuiSliderInt sliderSpeed,sliderR,sliderG,sliderB,sliderX,sliderY,sliderZ;
    private GuiButton SettingsOpen,SettingsClosed;
    private GuiButtonNetwork buttonRender;
    private GuiItemButton buttonInfo,buttonWork;
    GuiColorCube colorCube = new GuiColorCube();
    private boolean drawCube;
    private String WorkString;
    public GuiTorcherino(InventoryPlayer inventory, TileTorcherinoBase tileEntity, EntityPlayer player) {
        super(new ContainerTorcherino(inventory, tileEntity, player));
        this.tile = tileEntity;
        this.xSize = 288;
        this.ySize = 128;
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
        //Radius
        sliderX = new GuiSliderInt(tile, 1, x+10, y+20, 235, 20, min, tile.getValue(5), 15,"",4);
        sliderY = new GuiSliderInt(tile, 2, x+10, y+45, 235, 20, min, tile.getValue(5), 16,"",4);
        sliderZ = new GuiSliderInt(tile, 3, x+10, y+70, 235, 20, min, tile.getValue(5), 17,"",4);
        //Render
        sliderR = new GuiSliderInt(tile, 4, x+10, y+20, 185, 20, min, maxColor, 8,"",4);
        sliderG = new GuiSliderInt(tile, 5, x+10, y+45, 185, 20, min, maxColor, 9,"",4);
        sliderB = new GuiSliderInt(tile, 6, x+10, y+70, 185, 20, min, maxColor, 10,"",4);
        //Speed
        sliderSpeed = new GuiSliderInt(tile, 7, x+10, y+95, 235, 20, min, tile.getValue(6), 2,"",4);
        //ItemButton
        buttonInfo = new GuiItemButton(tile,8, x+250, y+20,0);
        buttonWork = new GuiItemButton(tile,9, x+250, y+45,1);
        //Button
        SettingsOpen = new GuiButton(10, x+250, y+70, 20, 20, "[R]");
        SettingsClosed = new GuiButton(11,x+10,y+95,30,20,"<-");
        //NetworkButton
        buttonRender = new GuiButtonNetwork(tile,12, x+45, y+95, 200, 20, "",2);
        //OTHER
        buttonInfo.setStackRender(new ItemStack(Items.PAPER));
        this.ConfigRenderUpdate();
        buttonList.add(buttonInfo);
        buttonList.add(buttonWork);
        buttonList.add(sliderSpeed);
        buttonList.add(sliderX);
        buttonList.add(sliderY);
        buttonList.add(sliderZ);
        this.OffStrings();
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
    private void OffMain() {
        setButtonStatus(new net.minecraft.client.gui.GuiButton[] {buttonWork, sliderX, sliderY, sliderZ, sliderSpeed, SettingsOpen,buttonInfo}, false, false);
        setButtonStatus(new net.minecraft.client.gui.GuiButton[] {buttonRender, sliderR, sliderG, sliderB, SettingsClosed}, true, true);
    }
    private void OffStrings() {
        setButtonStatus(new net.minecraft.client.gui.GuiButton[] {sliderR, sliderG, sliderB, buttonRender, SettingsClosed}, false, false);
        setButtonStatus(new net.minecraft.client.gui.GuiButton[] {buttonWork, sliderX, sliderY, sliderZ, sliderSpeed, SettingsOpen,buttonInfo}, true, true);
    }
    @Override
    protected void actionPerformed(net.minecraft.client.gui.GuiButton button) {
        if(button.id == 10){
            this.OffMain();
            drawCube = true;
        }
        if(button.id == 11){
            this.OffStrings();
            this.DelCube();
        }
    }
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX,mouseY);
        this.DrawName();
        this.UpdateButton();
        this.UpdateSliderString();
        if(Config.BooleanRender) {
            this.AddCube();
        }
        for (GuiButton button : buttonList) {
            if (button.isMouseOver()) {
                if (button == buttonInfo) {
                    int MaxRadius = tile.getValue(5);
                    int MaxSpeed = (tile.getValue(7) * 100 * tile.getValue(6));
                    drawHoveringText(TextFormatting.GREEN+"Info",mouseX,mouseY-16);
                    drawHoveringText("Max " + LS.StrTextRadius + ": " + MaxRadius+"x"+MaxRadius+"x"+MaxRadius, mouseX, mouseY+16);
                    drawHoveringText("Max " + LS.StrTextSpeed + ": " + MaxSpeed + "%", mouseX, mouseY);
                }
            }
            if (button.isMouseOver()) {
                if (button == buttonWork) {
                    drawHoveringText(WorkString,mouseX,mouseY);
                }
            }
        }
    }
    private void UpdateSliderString(){
        int speed = (tile.getValue(2) * 100 * tile.getValue(7));
        String speedString = (LS.StrTextSpeed + ": " + speed + "%");
        String stringR = ("Red: " + tile.getValue(8));
        String stringG = ("Green: " + tile.getValue(9));
        String stringB = ("Blue: " + tile.getValue(10));
        String radiusX = ("X Range: "+tile.getValue(15));
        String radiusY = ("Y Range: "+tile.getValue(16));
        String radiusZ = ("Z Range: "+tile.getValue(17));
        sliderX.displayString = radiusX;
        sliderY.displayString = radiusY;
        sliderZ.displayString = radiusZ;
        sliderR.displayString = stringR;
        sliderG.displayString = stringG;
        sliderB.displayString = stringB;
        sliderSpeed.displayString = speedString;
    }
    private void UpdateButton() {
        int renderGet = tile.getValue(4);
        int workGet = tile.getValue(3);
        switch (renderGet) {
            case 0: buttonRender.displayString = LS.StrTextRenderNull;break;
            case 1: buttonRender.displayString = LS.StrTextRenderLine;break;
            case 2: buttonRender.displayString = LS.StrTextRenderBox;break;
            case 3: buttonRender.displayString = LS.StrTextRenderComb;break;
        }
        switch (workGet) {
            case 0: WorkString = LS.StrTextWorking;buttonWork.setStackRender(new ItemStack(Blocks.REDSTONE_LAMP));break;
            case 1: WorkString = LS.StrTextAlways;buttonWork.setStackRender(new ItemStack(Blocks.REDSTONE_BLOCK));break;
            case 2: WorkString = LS.StrRedstoneMode;buttonWork.setStackRender(new ItemStack(Items.REDSTONE));break;
            case 3: WorkString = LS.StrRedstoneModeRevers;buttonWork.setStackRender(new ItemStack(Blocks.REDSTONE_TORCH));break;
        }
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
    private void DrawName(){
        int x = (this.width - xSize) / 2;
        int y = (this.height - ySize) / 2;
        this.fontRenderer.drawString(tile.getBlockType().getLocalizedName(), x+10,y+10, Color.WHITE.getRGB());
    }
    private void AddCube(){
        if(drawCube) {
            int x = (this.width - xSize) / 2;
            int y = (this.height - ySize) / 2;
            float red = sliderR.getSliderValue() / 255f;
            float green = sliderG.getSliderValue() / 255f;
            float blue = sliderB.getSliderValue() / 255f;
            colorCube.setCube(70, x+235, y+55, red, green, blue);
            colorCube.drawCube();
        }
    }
    private void DelCube(){
        drawCube = false;
        colorCube.clearCube();
    }
}