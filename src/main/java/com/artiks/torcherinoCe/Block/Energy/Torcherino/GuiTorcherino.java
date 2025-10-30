package com.artiks.torcherinoCe.Block.Energy.Torcherino;

import com.artiks.torcherinoCe.Block.Core.ExampleGuiContainer;
import com.artiks.torcherinoCe.Gui.*;
import com.artiks.torcherinoCe.network.Packet.UpdateTilePacketButton;
import com.artiks.torcherinoCe.network.RegistryNetwork;
import com.artiks.torcherinoCe.utility.LocalizedStringKey;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

@SideOnly(Side.CLIENT)
public class GuiTorcherino extends ExampleGuiContainer {

    private final TileTorcherinoBase tileEntity;
    private GuiSliderInt sliderSpeed,sliderRGB,sliderRGBC,sliderX,sliderY,sliderZ,sliderAlpha;
    private GuiItemButton buttonInfo,buttonWork;
    private GuiButtonImage buttonRender;
    private GuiButtonImage buttonSettings;
    private GuiButtonImage buttonTimer;
    private GuiButtonNetwork b1,b5,b10,b50,b100,b500,reset,start;
    private String WorkString,RenderString;
    private int timer = 0;
    private boolean isSettingOn = false;
    private boolean isSettingTimerOn = false;

    public GuiTorcherino(InventoryPlayer inventory, TileTorcherinoBase tileEntity) {
        super(new ContainerTorcherino(inventory, tileEntity));
        this.tileEntity = tileEntity;
        setTexture("textures/gui/torch.png", 256, 99);
        BarComponent barComponent = new BarComponent(this, 1, 10, 31, 162, 0, 17, 60, "textures/gui/gui_component.png", BarComponent.SideEnum.UP);
        addBarComponent(barComponent);
    }
    @Override
    public void Tick() {
        SetEnergyBarTooltips(1,tileEntity.getValue(18),tileEntity.getMaxEnergyStorage(),tileEntity.getValue(20),true);
        setBarValue(1,tileEntity.getValue(18),tileEntity.getMaxEnergyStorage());
    }
    @Override
    public void TickScreen() {
        this.DrawToolTipInfoButton();
        this.UpdateImageButton();
        this.UpdateButtonStringRender();
        this.UpdateSliderString();
        this.UpdateTimerButton();
        this.DrawTextTimer();
        this.getSliderColor();
    }
    @Override
    public void initGui() {
        super.initGui();
        int ScaledX = (this.width - this.xSize) / 2;
        int ScaledY = (this.height - this.ySize) / 2;
        this.buttonList.clear();
        //Radius
        sliderX = new GuiSliderInt(tileEntity, 1, ScaledX+40, ScaledY+5, 155, 20, 0, tileEntity.getValue(4), "RadiusX","",4);
        sliderY = new GuiSliderInt(tileEntity, 2, ScaledX+40, ScaledY+28, 155, 20, 0, tileEntity.getValue(4), "RadiusY","",4);
        sliderZ = new GuiSliderInt(tileEntity, 3, ScaledX+40, ScaledY+51, 155, 20, 0, tileEntity.getValue(4), "RadiusZ","",4);
        sliderSpeed = new GuiSliderInt(tileEntity, 4, ScaledX+40, ScaledY+74, 155, 20, 0, tileEntity.getValue(6), "currentMode","",4);
        //Render
        sliderRGB = new GuiSliderInt(tileEntity, 5, ScaledX+40, ScaledY+5, 155, 20, 0, 800, "rgbLine","",4);
        sliderRGBC = new GuiSliderInt(tileEntity, 6, ScaledX+40, ScaledY+28, 155, 20, 0, 800, "rgbCube","",4);
        sliderAlpha = new GuiSliderInt(tileEntity,7,ScaledX+40,ScaledY+51, 155, 20, 0, 100, "alpha", "Alpha", 2);
        buttonRender = new GuiButtonImage(tileEntity,8,ScaledX+231,ScaledY+28,2);
        //ItemButton-NetworkButton
        buttonInfo = new GuiItemButton(tileEntity,9, ScaledX+8, ScaledY+5,0);
        buttonWork = new GuiItemButton(tileEntity,10, ScaledX+231, ScaledY+5,1);
        //ItemButton-Info
        buttonInfo.setStackRender(new ItemStack(Items.PAPER));
        //ItemButton-Settings
        buttonSettings = new GuiButtonImage(tileEntity,12,ScaledX+205,ScaledY+28,0);
        buttonSettings.setTexture(16,16);
        //timer
        buttonTimer = new GuiButtonImage(tileEntity,20, ScaledX+205, ScaledY+5,0);
        buttonTimer.setTexture(16,16);
        b1 = new GuiButtonNetwork(tileEntity,21,ScaledX+40,ScaledY+5,40,20,"+1", 0);
        b5 = new GuiButtonNetwork(tileEntity,22,ScaledX+40,ScaledY+28,40,20,"+5", 0);
        b10 = new GuiButtonNetwork(tileEntity,23,ScaledX+90,ScaledY+5,40,20,"+10", 0);
        b50 = new GuiButtonNetwork(tileEntity,24,ScaledX+90,ScaledY+28,40,20,"+50", 0);
        b100 = new GuiButtonNetwork(tileEntity,25,ScaledX+140,ScaledY+5,40,20,"+100", 0);
        b500 = new GuiButtonNetwork(tileEntity,26,ScaledX+140,ScaledY+28,40,20,"+500", 0);
        start = new GuiButtonNetwork(tileEntity,27,ScaledX+40,ScaledY+51,40,20, LocalizedStringKey.StrStart, 3);
        reset = new GuiButtonNetwork(tileEntity,28,ScaledX+90,ScaledY+51,40,20,LocalizedStringKey.StrEnd, 4);
        //Init
        this.CreateButton();
        this.ButtonTimerOff();
        this.SettingOff();
    }
    private void getSliderColor(){
        Color color1 = Color.getHSBColor((sliderRGB.getSliderValue() / 600.0f) * 0.9f, 1.0f, 1.0f);
        float[] rgb1 = {color1.getRed() / 255.0f, color1.getGreen() / 255.0f, color1.getBlue() / 255.0f};
        Color color2 = Color.getHSBColor((sliderRGBC.getSliderValue() / 600.0f) * 0.9f, 1.0f, 1.0f);
        float[] rgb2 = {color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f};
        sliderRGB.setColorDragged(rgb1[0],rgb1[1],rgb1[2]);
        sliderRGBC.setColorDragged(rgb2[0],rgb2[1],rgb2[2]);
        sliderRGB.displayString =  "Rgb-line";
        sliderRGBC.displayString = "Rgb-cube";
    }
    private void DrawTextTimer() {
        if (isSettingTimerOn) {
            int ScaledX = (this.width - this.xSize) / 2;
            int ScaledY = (this.height - this.ySize) / 2;
            String h = LocalizedStringKey.StrH;
            String m = LocalizedStringKey.StrM;
            String s = LocalizedStringKey.StrS;
            String timerStr = LocalizedStringKey.StrTimer;
            int hours = timer / 3600;
            int minutes = (timer % 3600) / 60;
            int seconds = timer % 60;
            String timeString;
            if (timer < 60) {
                timeString = String.format("%s: %d %s", timerStr, seconds, s);
            } else if (timer < 3600) {
                timeString = String.format("%s: %d %s %d %s",
                        timerStr, minutes, m, seconds, s);
            } else {
                timeString = String.format("%s: %d %s %d %s %d %s",
                        timerStr, hours, h, minutes, m, seconds, s);
            }
            this.fontRenderer.drawString(timeString, ScaledX + 40, ScaledY + 74, Color.BLACK.getRGB());
        }
    }
    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 20:
                toggleTimer();
                break;
            case 12:
                toggleSettings();
                break;
            case 21:
                RegistryNetwork.network.sendToServer(new UpdateTilePacketButton(tileEntity.getPos(), 5));
                break;
            case 22:
                RegistryNetwork.network.sendToServer(new UpdateTilePacketButton(tileEntity.getPos(), 6));
                break;
            case 23:
                RegistryNetwork.network.sendToServer(new UpdateTilePacketButton(tileEntity.getPos(), 7));
                break;
            case 24:
                RegistryNetwork.network.sendToServer(new UpdateTilePacketButton(tileEntity.getPos(), 8));
                break;
            case 25:
                RegistryNetwork.network.sendToServer(new UpdateTilePacketButton(tileEntity.getPos(), 9));
                break;
            case 26:
                RegistryNetwork.network.sendToServer(new UpdateTilePacketButton(tileEntity.getPos(), 10));
                break;
        }
    }
    private void UpdateTimerButton() {
        timer = tileEntity.getValue(1);
        boolean timerRunning = tileEntity.getValue(2) == 1;
        boolean canAdjust = !timerRunning && isSettingTimerOn;
        b1.enabled = canAdjust;
        b5.enabled = canAdjust;
        b10.enabled = canAdjust;
        b50.enabled = canAdjust;
        b100.enabled = canAdjust;
        b500.enabled = canAdjust;
        start.enabled = canAdjust && timer > 0;
    }
    private void CreateButton() {
        buttonList.addAll(Arrays.asList(
                buttonRender, buttonInfo, buttonWork, buttonSettings, sliderX, sliderY, sliderZ, sliderSpeed,
                sliderRGB,sliderRGBC, sliderAlpha, buttonTimer, b1, b10, b100, b5, b50, b500, reset, start
        ));
    }
    private void toggleTimer() {
        if (isSettingTimerOn) {
            SettingTimerOff();
            buttonTimer.setTexture(16, 16);
        } else {
            SettingTimerOn();
            buttonTimer.setTexture(0, 16);
        }
        isSettingTimerOn = !isSettingTimerOn;
    }
    private void toggleSettings() {
        if (isSettingOn) {
            SettingOff();
            buttonSettings.setTexture(16, 16);
        } else {
            SettingOn();
            buttonSettings.setTexture(0, 16);
        }
        isSettingOn = !isSettingOn;
    }
    private void ButtonTimerOff(){
        setButtonsState(Arrays.asList(b1,b10,b100,b5,b50,b500,reset,start),false);
    }
    private void ButtonTimerOn(){
        setButtonsState(Arrays.asList(b1,b10,b100,b5,b50,b500,reset,start),true);
    }
    private void SettingTimerOff() {
        this.ButtonTimerOff();
        setSlidersState(Arrays.asList(sliderX, sliderY, sliderZ, sliderSpeed), true);
        setButtonsState(Arrays.asList(buttonSettings,buttonWork,buttonRender),true);
    }
    private void SettingTimerOn() {
        this.ButtonTimerOn();
        setSlidersState(Arrays.asList(sliderX, sliderY, sliderZ, sliderSpeed), false);
        setButtonsState(Arrays.asList(buttonSettings,buttonWork,buttonRender),false);
    }
    private void SettingOff() {
        setSlidersState(Arrays.asList(sliderRGB, sliderAlpha,sliderRGBC), false);
        setSlidersState(Arrays.asList(sliderX, sliderY, sliderZ, sliderSpeed), true);
        setButtonsState(Arrays.asList(buttonTimer,buttonWork,buttonRender),true);
    }
    private void SettingOn() {
        setSlidersState(Arrays.asList(sliderRGB, sliderAlpha,sliderRGBC), true);
        setSlidersState(Arrays.asList(sliderX, sliderY, sliderZ, sliderSpeed), false);
        setButtonsState(Arrays.asList(buttonTimer,buttonWork,buttonRender),false);
    }
    private void setButtonsState(List<? extends GuiButton> buttons, boolean enabled) {
        for (GuiButton button : buttons) {
            button.enabled = enabled;
            button.visible = enabled;
        }
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
                    int MaxRadius = tileEntity.getValue(4);
                    int MaxSpeed = (tileEntity.getValue(5) * 100 * tileEntity.getValue(6));
                    List<String> tooltipLines = Arrays.asList(
                            TextFormatting.GREEN + LocalizedStringKey.StrTextInfo,
                            TextFormatting.GRAY + "Max " + LocalizedStringKey.StrTextRadius + ": " + MaxRadius + "x" + MaxRadius + "x" + MaxRadius,
                            TextFormatting.GRAY + "Max " + LocalizedStringKey.StrTextSpeed + ": " + MaxSpeed + "%"
                    );
                    drawHoveringText(tooltipLines, getMouseX(), getMouseY() - 16);
                } else if (button.equals(buttonWork)) {
                    drawHoveringText(WorkString, getMouseX(), getMouseY());
                } else if (button.equals(buttonRender)) {
                    drawHoveringText(RenderString, getMouseX(), getMouseY());
                } else if (button.equals(buttonSettings)) {
                    drawHoveringText(LocalizedStringKey.RenderSettings, getMouseX(), getMouseY());
                } else if (button.equals(buttonTimer)) {
                    drawHoveringText(LocalizedStringKey.StrTimer, getMouseX(), getMouseY());
                }
            }
        }
    }
    private void UpdateSliderString(){
        int speed = (tileEntity.getValue(19) * 100 * tileEntity.getValue(5));
        sliderX.displayString = ("X: "+LocalizedStringKey.StrSliderRange + " " +tileEntity.getValue(15));
        sliderY.displayString = ("Y: "+LocalizedStringKey.StrSliderRange + " " +tileEntity.getValue(16));
        sliderZ.displayString = ("Z: "+LocalizedStringKey.StrSliderRange + " " +tileEntity.getValue(17));
        sliderSpeed.displayString = (LocalizedStringKey.StrTextSpeed + ": " + speed + "%");
    }
    private void UpdateImageButton(){
        int getRender = tileEntity.getValue(11);
        switch (getRender){
            case 0: buttonRender.setTexture(0,16);break;
            case 1: buttonRender.setTexture(0,0);break;
            case 2: buttonRender.setTexture(16,0);break;
            case 3: buttonRender.setTexture(32,0);break;
            case 4: buttonRender.setTexture(48,0);break;
            case 5: buttonRender.setTexture(64,0);break;
        }
    }
    private void UpdateButtonStringRender() {
        int render = tileEntity.getValue(11);
        int work = tileEntity.getValue(3);

        String[] renderStrings = {
                TextFormatting.RED + LocalizedStringKey.StrTextRenderNull,
                TextFormatting.GREEN + LocalizedStringKey.StrTextRenderLine,
                TextFormatting.GREEN + LocalizedStringKey.StrTextRenderBox,
                TextFormatting.GREEN + LocalizedStringKey.StrTextRenderComb,
                TextFormatting.GREEN + LocalizedStringKey.StrTextRenderWave,
                TextFormatting.GREEN + LocalizedStringKey.StrTextRenderCube,
        };
        RenderString = renderStrings[render];

        enumRedstoneControllerButton button = enumRedstoneControllerButton.getByIndex(work);
        WorkString = button.getDisplayText();
        buttonWork.setStackRender(button.getStack());
    }
}