package com.ariks.torcherino.Block.EnergyGeneration;

import com.ariks.torcherino.Gui.BarComponent;
import com.ariks.torcherino.Block.Core.ExampleGuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiEnergyParticle extends ExampleGuiContainer {
    private final TileEnergyParticle tile;

    public GuiEnergyParticle(InventoryPlayer inventory, TileEnergyParticle tileEntity, EntityPlayer player) {
        super(new ContainerEnergyParticle(inventory, tileEntity, player));
        this.tile = tileEntity;
        setTexture("textures/gui/gui_energy.png", 175, 167);
        BarComponent barComponent = new BarComponent(this,1,9,7,197,0,16,61,"textures/gui/gui_component.png");
        barComponent.setSideDirection("up");
        BarComponent barComponent2 = new BarComponent(this,2,32,29,43,53,24,16,"textures/gui/gui_component.png");
        barComponent2.setSideDirection("left");
        addBarComponent(barComponent);
        addBarComponent(barComponent2);
    }
    @Override
    public void Tick() {
        String formattedValueMin = numberFormat.format(tile.getValue(1));
        String formattedValueMax = numberFormat.format(tile.getValue(2));
        setTooltipBar(1,"RF: "+formattedValueMin+ " / " + formattedValueMax);
        setBarValue(1,tile.getValue(1),tile.getValue(2));
        setBarValue(2,tile.getValue(3),tile.getValue(4));
    }
}