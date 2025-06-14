package com.ariks.torcherinoCe.Block.Torcherino;

import com.ariks.torcherinoCe.Block.Core.ExampleGuiContainer;
import com.ariks.torcherinoCe.Gui.*;
import com.ariks.torcherinoCe.Register.RegistryNetwork;
import com.ariks.torcherinoCe.network.UpdateTileValue;
import com.ariks.torcherinoCe.utility.EnergyFormat;
import com.ariks.torcherinoCe.utility.LocalizedStringKey;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
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

    private final TileTorcherinoBase tile;
    private final GuiColorCube colorCube = new GuiColorCube();
    private final LocalizedStringKey LS = new LocalizedStringKey();
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

    public GuiTorcherino(InventoryPlayer inventory, TileTorcherinoBase tileEntity, EntityPlayer player) {
        super(new ContainerTorcherino(inventory, tileEntity, player));
        this.tile = tileEntity;
        setTexture("textures/gui/torch.png", 256, 99);
        BarComponent barComponent = new BarComponent(this, 1, 10, 31, 162, 0, 17, 60, "textures/gui/gui_component.png");
        barComponent.setSideDirection("up");
        addBarComponent(barComponent);
    }
    @Override
    public void Tick() {
        String formattedValueMin = EnergyFormat.formatNumber(tile.getValue(18));
        String formattedValueMax = EnergyFormat.formatNumber(tile.getValue(21));
        setTooltipBar(1, formattedValueMin + " / " + formattedValueMax);
        setBarValue(1, tile.getValue(18), tile.getValue(21));
    }
    @Override
    public void TickScreen() {
        this.DrawToolTipInfoButton();
        this.DrawCube();
        this.UpdateImageButton();
        this.UpdateButtonStringRender();
        this.UpdateSliderString();
        this.UpdateTimerButton();
        this.DrawTextTimer();
    }
    @Override
    public void initGui() {
        super.initGui();
        int ScaledX = (this.width - this.xSize) / 2;
        int ScaledY = (this.height - this.ySize) / 2;
        this.buttonList.clear();
        //Radius
        sliderX = new GuiSliderInt(tile, 1, ScaledX+40, ScaledY+5, 155, 20, 0, tile.getValue(4), 15,"",4);
        sliderY = new GuiSliderInt(tile, 2, ScaledX+40, ScaledY+28, 155, 20, 0, tile.getValue(4), 16,"",4);
        sliderZ = new GuiSliderInt(tile, 3, ScaledX+40, ScaledY+51, 155, 20, 0, tile.getValue(4), 17,"",4);
        sliderSpeed = new GuiSliderInt(tile, 4, ScaledX+40, ScaledY+74, 155, 20, 0, tile.getValue(6), 19,"",4);
        //Render
        sliderRGB = new GuiSliderInt(tile, 5, ScaledX+40, ScaledY+5, 155, 20, 0, 800, 8,"Rgb-line",2);
        sliderRGBC = new GuiSliderInt(tile, 6, ScaledX+40, ScaledY+28, 155, 20, 0, 800, 9,"Rgb-cube",2);
        sliderAlpha = new GuiSliderInt(tile,7,ScaledX+40,ScaledY+51, 155, 20, 0, 100, 10, "Alpha", 2);
        buttonRender = new GuiButtonImage(tile,8,ScaledX+231,ScaledY+28,2);
        //ItemButton-NetworkButton
        buttonInfo = new GuiItemButton(tile,9, ScaledX+8, ScaledY+5,0);
        buttonWork = new GuiItemButton(tile,10, ScaledX+231, ScaledY+5,1);

        //ItemButton-Info
        buttonInfo.setStackRender(new ItemStack(Items.PAPER));
        //ItemButton-Settings
        buttonSettings = new GuiButtonImage(tile,12,ScaledX+205,ScaledY+28,0);
        buttonSettings.setTexture(16,16);
        //timer
        buttonTimer = new GuiButtonImage(tile,20, ScaledX+205, ScaledY+5,0);
        buttonTimer.setTexture(16,16);
        b1 = new GuiButtonNetwork(tile,21,ScaledX+40,ScaledY+5,40,20,"+1", 0);
        b5 = new GuiButtonNetwork(tile,22,ScaledX+40,ScaledY+28,40,20,"+5", 0);
        b10 = new GuiButtonNetwork(tile,23,ScaledX+90,ScaledY+5,40,20,"+10", 0);
        b50 = new GuiButtonNetwork(tile,24,ScaledX+90,ScaledY+28,40,20,"+50", 0);
        b100 = new GuiButtonNetwork(tile,25,ScaledX+140,ScaledY+5,40,20,"+100", 0);
        b500 = new GuiButtonNetwork(tile,26,ScaledX+140,ScaledY+28,40,20,"+500", 0);
        start = new GuiButtonNetwork(tile,27,ScaledX+40,ScaledY+51,40,20,LS.StrStart, 3);
        reset = new GuiButtonNetwork(tile,28,ScaledX+90,ScaledY+51,40,20,LS.StrEnd, 4);
        //Init
        this.CreateButton();
        this.ButtonTimerOff();
        this.SettingOff();
    }
    private void DrawTextTimer() {
        if (isSettingTimerOn) {
            int ScaledX = (this.width - this.xSize) / 2;
            int ScaledY = (this.height - this.ySize) / 2;
            String h = LS.StrH;
            String m = LS.StrM;
            String s = LS.StrS;
            String timerStr = LS.StrTimer;
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
            case 21: case 22: case 23: case 24: case 25: case 26:
                int[] increments = {1, 5, 10, 50, 100, 500};
                RegistryNetwork.network.sendToServer(new UpdateTileValue(tile.getPos(), 50 , increments[button.id - 21]));
                break;
        }
    }
    private void UpdateTimerButton() {
        timer = tile.getValue(1);
        boolean timerRunning = tile.getValue(2) == 1;
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
                    int MaxRadius = tile.getValue(4);
                    int MaxSpeed = (tile.getValue(5) * 100 * tile.getValue(6));
                    String formattedValueRf = EnergyFormat.formatNumber(tile.getValue(20));
                    List<String> tooltipLines = Arrays.asList(
                            TextFormatting.GREEN + LS.StrTextInfo,
                            TextFormatting.GRAY + "Max " + LS.StrTextRadius + ": " + MaxRadius + "x" + MaxRadius + "x" + MaxRadius,
                            TextFormatting.GRAY + "Max " + LS.StrTextSpeed + ": " + MaxSpeed + "%",
                            TextFormatting.RED + LS.StrRFTick + " " + formattedValueRf
                    );
                    drawHoveringText(tooltipLines, getMouseX(), getMouseY() - 16);
                } else if (button.equals(buttonWork)) {
                    drawHoveringText(WorkString, getMouseX(), getMouseY());
                } else if (button.equals(buttonRender)) {
                    drawHoveringText(RenderString, getMouseX(), getMouseY());
                } else if (button.equals(buttonSettings)) {
                    drawHoveringText(LS.RenderSettings, getMouseX(), getMouseY());
                } else if (button.equals(buttonTimer)) {
                    drawHoveringText(LS.StrTimer, getMouseX(), getMouseY());
                }
            }
        }
    }
    private void UpdateSliderString(){
        int speed = (tile.getValue(19) * 100 * tile.getValue(5));
        sliderX.displayString = ("X: "+LS.StrSliderRange + " " +tile.getValue(15));
        sliderY.displayString = ("Y: "+LS.StrSliderRange + " " +tile.getValue(16));
        sliderZ.displayString = ("Z: "+LS.StrSliderRange + " " +tile.getValue(17));
        sliderSpeed.displayString = (LS.StrTextSpeed + ": " + speed + "%");
    }
    private void UpdateImageButton(){
        int getRender = tile.getValue(11);
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
        int render = tile.getValue(11);
        int work = tile.getValue(3);
        String[] renderStrings = {
                TextFormatting.RED + LS.StrTextRenderNull,
                TextFormatting.GREEN + LS.StrTextRenderLine,
                TextFormatting.GREEN + LS.StrTextRenderBox,
                TextFormatting.GREEN + LS.StrTextRenderComb,
                TextFormatting.GREEN + LS.StrTextRenderWave,
                TextFormatting.GREEN + LS.StrTextRenderCube,
        };
        RenderString = renderStrings[render];
        String[] workStrings = {
                TextFormatting.RED + LS.StrTextWorking,
                TextFormatting.GREEN + LS.StrTextAlways,
                TextFormatting.GREEN + LS.StrRedstoneMode,
                TextFormatting.GREEN + LS.StrRedstoneModeRevers
        };
        ItemStack[] workIcons = {
                new ItemStack(Blocks.REDSTONE_LAMP),
                new ItemStack(Blocks.REDSTONE_BLOCK),
                new ItemStack(Items.REDSTONE),
                new ItemStack(Blocks.REDSTONE_TORCH)
        };
        WorkString = workStrings[work];
        buttonWork.setStackRender(workIcons[work]);
    }
    private void DrawCube() {
        Color color = Color.getHSBColor((sliderRGB.getSliderValue() / 600.0f) * 0.9f, 1.0f, 1.0f);
        float[] rgb = {color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f};
        int posX = (this.width - this.xSize) / 2 + 205;
        int posY = (this.height - this.ySize) / 2 + 58;
        colorCube.setCube(46, 36, posX, posY, rgb[0], rgb[1], rgb[2]);
        colorCube.drawCube();
    }
}