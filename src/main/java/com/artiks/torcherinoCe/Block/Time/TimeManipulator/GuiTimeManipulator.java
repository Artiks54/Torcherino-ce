package com.artiks.torcherinoCe.Block.Time.TimeManipulator;

import com.artiks.torcherinoCe.Block.Core.ExampleGuiContainer;
import com.artiks.torcherinoCe.Gui.BarComponent;
import com.artiks.torcherinoCe.Gui.GuiButtonNetwork;
import com.artiks.torcherinoCe.utility.LocalizedStringKey;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.text.TextFormatting;

public class GuiTimeManipulator extends ExampleGuiContainer {

    private final TileTimeManipulator tileEntity;
    private GuiButtonNetwork buttonSetDay;
    private GuiButtonNetwork buttonSetNight;
    private GuiButtonNetwork buttonSetRain;
    private GuiButtonNetwork buttonSetRainClear;
    public GuiTimeManipulator(InventoryPlayer inventory, TileTimeManipulator tileEntity) {
        super(new ContainerTimeManipulator(inventory,tileEntity));
        this.tileEntity = tileEntity;
        setTexture("textures/gui/gui_time.png", 175, 167);
        BarComponent barComponent = new BarComponent(this,1,8,16,0,19,159,16,"textures/gui/gui_component.png", BarComponent.SideEnum.LEFT);
        addBarComponent(barComponent);
    }
    @Override
    public void Tick() {
        SetBarTooltips(1,tileEntity.getEnergyTime(),tileEntity.getMaxTimeStored(),true);
        setBarValue(1,tileEntity.getEnergyTime(),tileEntity.getMaxTimeStored());
        if(tileEntity.getEnergyTime() < tileEntity.getRequiredTimeManipulator()) {
            buttonSetDay.enabled = false;
            buttonSetNight.enabled = false;
            buttonSetRain.enabled = false;
            buttonSetRainClear.enabled = false;
        }if(tileEntity.getEnergyTime() >= tileEntity.getRequiredTimeManipulator()){
            buttonSetDay.enabled = true;
            buttonSetNight.enabled = true;
            buttonSetRain.enabled = true;
            buttonSetRainClear.enabled = true;
        }
    }
    @Override
    public void initGui() {
        super.initGui();
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        buttonList.clear();
        buttonSetDay = new GuiButtonNetwork(tileEntity,1, x+5, y+40, 70, 20, TextFormatting.YELLOW+ LocalizedStringKey.StrTextDay,1);
        buttonSetNight = new GuiButtonNetwork(tileEntity,2, x+101, y+40, 70, 20, TextFormatting.DARK_PURPLE+ LocalizedStringKey.StrTextNight,2);
        buttonSetRain = new GuiButtonNetwork(tileEntity,3, x+5, y+62, 70, 20, TextFormatting.AQUA+ LocalizedStringKey.StrTextRain,3);
        buttonSetRainClear = new GuiButtonNetwork(tileEntity,4, x+101, y+62, 70, 20, TextFormatting.DARK_AQUA+ LocalizedStringKey.StrTextRainClear,4);
        buttonList.add(buttonSetRain);
        buttonList.add(buttonSetRainClear);
        buttonList.add(buttonSetDay);
        buttonList.add(buttonSetNight);
    }
    @Override
    public void TickScreen() {
        this.DrawToolTipInfoButton();
    }
    private void DrawToolTipInfoButton() {
        for (GuiButton button : buttonList) {
            if (button.isMouseOver() && (button.equals(buttonSetDay) ||
                    button.equals(buttonSetNight) ||
                    button.equals(buttonSetRainClear) ||
                    button.equals(buttonSetRain))) {
                drawHoveringText(LocalizedStringKey.StrTextNeedTimeManipulator+": " + tileEntity.getRequiredTimeManipulator(), getMouseX(), getMouseY());
                break;
            }
        }
    }
}