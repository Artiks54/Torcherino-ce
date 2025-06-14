package com.ariks.torcherinoCe.Block.Time.TimeManipulator;

import com.ariks.torcherinoCe.Block.Core.ExampleGuiContainer;
import com.ariks.torcherinoCe.Gui.BarComponent;
import com.ariks.torcherinoCe.Gui.GuiButtonNetwork;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.text.TextFormatting;

public class GuiTimeManipulator extends ExampleGuiContainer {

    private final TileTimeManipulator tile;
    private GuiButtonNetwork buttonSetDay;
    private GuiButtonNetwork buttonSetNight;
    private GuiButtonNetwork buttonSetRain;
    private GuiButtonNetwork buttonSetRainClear;
    public GuiTimeManipulator(InventoryPlayer inventory, TileTimeManipulator tileEntity) {
        super(new ContainerTimeManipulator(inventory,tileEntity));
        this.tile = tileEntity;
        setTexture("textures/gui/gui_time.png", 175, 167);
        BarComponent barComponent = new BarComponent(this,1,8,16,0,19,159,16,"textures/gui/gui_component.png");
        addBarComponent(barComponent);
    }
    @Override
    public void Tick() {
        String formattedValueMin = numberFormat.format(tile.getValue(1));
        String formattedValueMax = numberFormat.format(tile.getMaxTimeStored());
        setTooltipBar(1,LS.StrTime+ " " +formattedValueMin+ " / " + formattedValueMax);
        setBarValue(1,tile.getValue(1),tile.getMaxTimeStored());
        if(tile.getValue(1) < tile.getRequiredTimeManipulator()) {
            buttonSetDay.enabled = false;
            buttonSetNight.enabled = false;
            buttonSetRain.enabled = false;
            buttonSetRainClear.enabled = false;
        }if(tile.getValue(1) >= tile.getRequiredTimeManipulator()){
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
        buttonSetDay = new GuiButtonNetwork(tile,1, x+5, y+40, 70, 20, TextFormatting.YELLOW+ LS.StrTextDay,1);
        buttonSetNight = new GuiButtonNetwork(tile,2, x+101, y+40, 70, 20, TextFormatting.DARK_PURPLE+ LS.StrTextNight,2);
        buttonSetRain = new GuiButtonNetwork(tile,3, x+5, y+62, 70, 20, TextFormatting.AQUA+ LS.StrTextRain,3);
        buttonSetRainClear = new GuiButtonNetwork(tile,4, x+101, y+62, 70, 20, TextFormatting.DARK_AQUA+ LS.StrTextRainClear,4);
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
                drawHoveringText(LS.StrTextNeedTimeManipulator+": " + tile.getValue(3), getMouseX(), getMouseY());
                break;
            }
        }
    }
}