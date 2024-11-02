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
        BarComponent barComponent = new BarComponent(this,1,8,16,0,0,159,16,"textures/gui/gui_component.png");
        barComponent.setSideDirection("left");
        addBarComponent(barComponent);
    }

    @Override
    public void Tick() {
        String formattedValueMin = numberFormat.format(tile.getValue(1));
        String formattedValueMax = numberFormat.format(tile.getValue(2));
        setTooltipBar(1,"RF: "+formattedValueMin+ " / " + formattedValueMax);
        setBarValue(1,tile.getValue(1),tile.getValue(2));
    }
}