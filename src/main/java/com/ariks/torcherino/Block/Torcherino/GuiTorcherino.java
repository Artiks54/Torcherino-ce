package com.ariks.torcherino.Block.Torcherino;

import com.ariks.torcherino.Block.Core.ExampleGuiContainer;
import com.ariks.torcherino.Gui.*;
import com.ariks.torcherino.util.*;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import java.util.Arrays;
import java.util.List;

@SideOnly(Side.CLIENT)
public class GuiTorcherino extends ExampleGuiContainer {

    private final TileTorcherinoBase tile;
    private final LocalizedStringKey LS = new LocalizedStringKey();
    private GuiSliderInt sliderSpeed,sliderR,sliderG,sliderB,sliderX,sliderY,sliderZ;
    private GuiItemButton buttonInfo,buttonWork;
    private final GuiColorCube colorCube = new GuiColorCube();
    private GuiButtonImage buttonRender;
    private GuiButtonImage buttonSettings;
    private boolean isSettingOn = false;
    private String WorkString,RenderString;

    public GuiTorcherino(InventoryPlayer inventory, TileTorcherinoBase tileEntity, EntityPlayer player) {
        super(new ContainerTorcherino(inventory, tileEntity, player));
        this.tile = tileEntity;
        setTexture("textures/gui/torch.png", 255, 99);
        BarComponent barComponent = new BarComponent(this, 1, 10, 6, 162, 0, 17, 85, "textures/gui/gui_component.png");
        barComponent.setSideDirection("up");
        addBarComponent(barComponent);
    }
    @Override
    public void Tick() {
        String formattedValueMin = numberFormat.format(tile.getValue(18));
        String formattedValueMax = numberFormat.format(tile.getValue(19));
        setTooltipBar(1, "RF: " + formattedValueMin + " / " + formattedValueMax);
        setBarValue(1, tile.getValue(18), tile.getValue(19));
    }
    @Override
    public void TickScreen() {
        this.DrawToolTipInfoButton();
        this.DrawCube();
        this.UpdateButtonTooltip();
        this.UpdateImageButtonRender();
        this.UpdateSliderString();
    }
    @Override
    public void initGui() {
        super.initGui();
        int ScaledX = (this.width - this.xSize) / 2;
        int ScaledY = (this.height - this.ySize) / 2;
        this.buttonList.clear();
        //Radius
        sliderX = new GuiSliderInt(tile, 1, ScaledX+40, ScaledY+5, 155, 20, 0, tile.getValue(5), 15,"",4);
        sliderY = new GuiSliderInt(tile, 2, ScaledX+40, ScaledY+28, 155, 20, 0, tile.getValue(5), 16,"",4);
        sliderZ = new GuiSliderInt(tile, 3, ScaledX+40, ScaledY+51, 155, 20, 0, tile.getValue(5), 17,"",4);
        //Render
        sliderR = new GuiSliderInt(tile, 4, ScaledX+40, ScaledY+5, 155, 20, 0, 255, 8,"",4);
        sliderG = new GuiSliderInt(tile, 5, ScaledX+40, ScaledY+28 , 155, 20, 0, 255, 9,"",4);
        sliderB = new GuiSliderInt(tile, 6, ScaledX+40, ScaledY+51, 155, 20, 0, 255, 10,"",4);
        //Speed
        sliderSpeed = new GuiSliderInt(tile, 7, ScaledX+40, ScaledY+74, 155, 20, 0, tile.getValue(6), 2,"",4);
        //ItemButton-NetworkButton
        buttonInfo = new GuiItemButton(tile,8, ScaledX+205, ScaledY+5,0);
        buttonWork = new GuiItemButton(tile,9, ScaledX+231, ScaledY+5,1);
        //ImageButton-NetworkButton
        buttonRender = new GuiButtonImage(tile,12,ScaledX+231,ScaledY+28,2);
        //ItemButton-Info
        buttonInfo.setStackRender(new ItemStack(Items.PAPER));
        //ItemButton-Settings
        buttonSettings = new GuiButtonImage(tile,13,ScaledX+205,ScaledY+28,0);
        buttonSettings.setTexture(16,16);
        this.CreateButton();
        this.SettingOff();
    }
    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.id == 13) {
            if (isSettingOn) {
                this.SettingOff();
                buttonSettings.setTexture(16,16);
            } else {
                this.SettingOn();
                buttonSettings.setTexture(0,16);
            }
            isSettingOn = !isSettingOn;
        }
    }
    private void CreateButton(){
        buttonList.add(buttonRender);
        buttonList.add(buttonInfo);
        buttonList.add(buttonWork);
        buttonList.add(buttonSettings);
        buttonList.add(sliderX);
        buttonList.add(sliderY);
        buttonList.add(sliderZ);
        buttonList.add(sliderSpeed);
        buttonList.add(sliderR);
        buttonList.add(sliderG);
        buttonList.add(sliderB);
    }
    private void SettingOff() {
        setSlidersState(Arrays.asList(sliderR, sliderG, sliderB), false);
        setSlidersState(Arrays.asList(sliderX, sliderY, sliderZ, sliderSpeed), true);
    }
    private void SettingOn() {
        setSlidersState(Arrays.asList(sliderR, sliderG, sliderB), true);
        setSlidersState(Arrays.asList(sliderX, sliderY, sliderZ, sliderSpeed), false);
    }
    private void setSlidersState(List<GuiSliderInt> sliders, boolean enabled) {
        for (GuiSliderInt slider : sliders) {
            slider.enabled = enabled;
            slider.visible = enabled;
        }
    }
    private void DrawToolTipInfoButton() {
        for (GuiButton button : buttonList) {
            if (button.isMouseOver()) {
                if (button.equals(buttonInfo)) {
                    int MaxRadius = tile.getValue(5);
                    int MaxSpeed = (tile.getValue(7) * 100 * tile.getValue(6));
                    String formattedValueRf = numberFormat.format(tile.getValue(20));
                    drawHoveringText(TextFormatting.GREEN + LS.StrTextInfo, getMouseX(), getMouseY() - 16);
                    drawHoveringText("Max " + LS.StrTextRadius + ": " + MaxRadius + "x" + MaxRadius + "x" + MaxRadius, getMouseX(), getMouseY() + 16);
                    drawHoveringText("Max " + LS.StrTextSpeed + ": " + MaxSpeed + "%", getMouseX(), getMouseY());
                    drawHoveringText(LS.StrRFTick + " " + formattedValueRf, getMouseX(), getMouseY() + 32);
                } else if (button.equals(buttonWork)) {
                    drawHoveringText(WorkString, getMouseX(), getMouseY());
                } else if (button.equals(buttonRender)) {
                    drawHoveringText(RenderString, getMouseX(), getMouseY());
                } else if (button.equals(buttonSettings)) {
                    drawHoveringText(LS.RenderSettings, getMouseX(), getMouseY());
                }
            }
        }
    }
    private void UpdateSliderString(){
        int speed = (tile.getValue(2) * 100 * tile.getValue(7));
        sliderX.displayString = ("X: "+LS.StrSliderRange + " " +tile.getValue(15));
        sliderY.displayString = ("Y: "+LS.StrSliderRange + " " +tile.getValue(16));
        sliderZ.displayString = ("Z: "+LS.StrSliderRange + " " +tile.getValue(17));
        sliderR.displayString = (LS.StrSliderR + " " + tile.getValue(8));
        sliderG.displayString = (LS.StrSliderG + " " + tile.getValue(9));
        sliderB.displayString = (LS.StrSliderB + " " + tile.getValue(10));
        sliderSpeed.displayString = (LS.StrTextSpeed + ": " + speed + "%");
    }
    private void UpdateImageButtonRender(){
        int getRender = tile.getValue(4);
        switch (getRender){
            case 0: buttonRender.setTexture(0,16);break;
            case 1: buttonRender.setTexture(0,0);break;
            case 2: buttonRender.setTexture(16,0);break;
            case 3: buttonRender.setTexture(32,0);break;
        }
    }
    private void UpdateButtonTooltip() {
        int render = tile.getValue(4);
        int work = tile.getValue(3);
        switch (render) {
            case 0: RenderString = LS.StrTextRenderNull;break;
            case 1: RenderString = LS.StrTextRenderLine;break;
            case 2: RenderString = LS.StrTextRenderBox;break;
            case 3: RenderString = LS.StrTextRenderComb;break;
        }
        switch (work) {
            case 0: WorkString = LS.StrTextWorking;buttonWork.setStackRender(new ItemStack(Blocks.REDSTONE_LAMP));break;
            case 1: WorkString = LS.StrTextAlways;buttonWork.setStackRender(new ItemStack(Blocks.REDSTONE_BLOCK));break;
            case 2: WorkString = LS.StrRedstoneMode;buttonWork.setStackRender(new ItemStack(Items.REDSTONE));break;
            case 3: WorkString = LS.StrRedstoneModeRevers;buttonWork.setStackRender(new ItemStack(Blocks.REDSTONE_TORCH));break;
        }
    }
    private void DrawCube(){
        float red = sliderR.getSliderValue() / 255f;
        float green = sliderG.getSliderValue() / 255f;
        float blue = sliderB.getSliderValue() / 255f;
        int ScaledX = (this.width - this.xSize) / 2;
        int ScaledY = (this.height - this.ySize) / 2;
        colorCube.setCube(46, 36, ScaledX + 205, ScaledY + 58, red, green, blue);
        colorCube.drawCube();
    }
}