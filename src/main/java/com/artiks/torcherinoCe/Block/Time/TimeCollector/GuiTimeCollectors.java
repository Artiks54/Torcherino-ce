package com.artiks.torcherinoCe.Block.Time.TimeCollector;

import com.artiks.torcherinoCe.Block.Core.ExampleGuiContainer;
import com.artiks.torcherinoCe.Gui.BarComponent;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiTimeCollectors extends ExampleGuiContainer {
    private final TileCollectors tile;

    public GuiTimeCollectors(InventoryPlayer inventory, TileCollectors tileEntity) {
        super(new ContainerTimeCollectors(inventory,tileEntity));
        this.tile = tileEntity;
        setTexture("textures/gui/gui_time.png", 175, 167);
        BarComponent barComponent = new BarComponent(this,1,8,16,0,19,159,16,"textures/gui/gui_component.png", BarComponent.SideEnum.LEFT);
        addBarComponent(barComponent);
    }
    @Override
    public void Tick() {
        SetBarTooltips(1,tile.getEnergyTime(),tile.getMaxTimeStored(),true);
        setBarValue(1,tile.getEnergyTime(),tile.getMaxTimeStored());
    }
}