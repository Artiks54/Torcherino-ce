package com.artiks.torcherinoCe.Block.Energy.EnergyTimeManipulator;

import com.artiks.torcherinoCe.Block.Core.ExampleGuiContainer;
import com.artiks.torcherinoCe.Gui.BarComponent;
import com.artiks.torcherinoCe.Gui.GuiButtonNetwork;
import com.artiks.torcherinoCe.utility.LocalizedStringKey;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.text.TextFormatting;

public class GuiEnergyTimeManipulator extends ExampleGuiContainer {

    private final TileEnergyTimeManipulator tile;
    private GuiButtonNetwork buttonSetDay;
    private GuiButtonNetwork buttonSetNight;
    private GuiButtonNetwork buttonSetRain;
    private GuiButtonNetwork buttonSetRainClear;

    public GuiEnergyTimeManipulator(InventoryPlayer inventory, TileEnergyTimeManipulator tileEntity) {
        super(new ContainerEnergyTimeManipulator(inventory,tileEntity));
        this.tile = tileEntity;
        setTexture("textures/gui/gui_time.png", 200, 167);
        BarComponent barComponent = new BarComponent(this,1,8,16,0,0,159,16,"textures/gui/gui_component.png", BarComponent.SideEnum.LEFT);
        addBarComponent(barComponent);
    }
    @Override
    public void initGui() {
        super.initGui();
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        buttonList.clear();
        buttonSetDay = new GuiButtonNetwork(tile,1, x+5, y+40, 70, 20, TextFormatting.YELLOW+ LocalizedStringKey.StrTextDay,1);
        buttonSetNight = new GuiButtonNetwork(tile,2, x+101, y+40, 70, 20, TextFormatting.DARK_PURPLE+ LocalizedStringKey.StrTextNight,2);
        buttonSetRain = new GuiButtonNetwork(tile,3, x+5, y+62, 70, 20, TextFormatting.AQUA+ LocalizedStringKey.StrTextRain,3);
        buttonSetRainClear = new GuiButtonNetwork(tile,4, x+101, y+62, 70, 20, TextFormatting.DARK_AQUA+ LocalizedStringKey.StrTextRainClear,4);
        buttonList.add(buttonSetRain);
        buttonList.add(buttonSetRainClear);
        buttonList.add(buttonSetDay);
        buttonList.add(buttonSetNight);
    }
    @Override
    public void Tick() {
        SetEnergyBarTooltips(1,tile.getEnergyStored(),tile.getMaxEnergyStorage(),tile.getNeedEnergy(),true);
        setBarValue(1,tile.getEnergyStored(),tile.getMaxEnergyStorage());
        if(tile.getEnergyStored() < tile.getNeedEnergy()) {
            buttonSetDay.enabled = false;
            buttonSetNight.enabled = false;
            buttonSetRain.enabled = false;
            buttonSetRainClear.enabled = false;
        }if(tile.getEnergyStored() >= tile.getNeedEnergy()){
            buttonSetDay.enabled = true;
            buttonSetNight.enabled = true;
            buttonSetRain.enabled = true;
            buttonSetRainClear.enabled = true;
        }
    }
}