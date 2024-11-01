package com.ariks.torcherino.Block.TimeManipulator;

import com.ariks.torcherino.Block.ExampleGuiContainer;
import com.ariks.torcherino.Gui.BarComponent;
import com.ariks.torcherino.Gui.GuiButtonNetwork;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.text.TextFormatting;

public class GuiTimeManipulator extends ExampleGuiContainer {

    private final TileTimeManipulator tile;
    private GuiButtonNetwork buttonSetDay,buttonSetNight;
    public GuiTimeManipulator(InventoryPlayer inventory, TileTimeManipulator tileEntity, EntityPlayer player) {
        super(new ContainerTimeManipulator(inventory,tileEntity,player));
        this.tile = tileEntity;
        setTexture("textures/gui/gui_time.png", 175, 167);
        BarComponent barComponent = new BarComponent(this,1,8,16,0,19,159,16,"textures/gui/gui_component.png");
        addBarComponent(barComponent);
    }
    @Override
    public void Tick() {
        String formattedValueMin = numberFormat.format(tile.getValue(1));
        String formattedValueMax = numberFormat.format(tile.getValue(2));
        setTooltipBar(1,"Time: "+formattedValueMin+ " / " + formattedValueMax);
        setBarValue(1,tile.getValue(1),tile.getValue(2));
        if(tile.getValue(1) < tile.getValue(2)) {
            buttonSetDay.enabled = false;
            buttonSetNight.enabled = false;
        }if(tile.getValue(1) >= tile.getValue(2)){
            buttonSetDay.enabled = true;
            buttonSetNight.enabled = true;
        }
    }
    @Override
    public void initGui() {
        super.initGui();
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        buttonList.clear();
        buttonSetDay = new GuiButtonNetwork(tile,1, x+5, y+50, 70, 20, TextFormatting.YELLOW+ LS.StrTextDay,1);
        buttonSetNight = new GuiButtonNetwork(tile,2, x+101, y+50, 70, 20, TextFormatting.DARK_PURPLE+ LS.StrTextNight,2);
        buttonList.add(buttonSetDay);
        buttonList.add(buttonSetNight);
    }
}