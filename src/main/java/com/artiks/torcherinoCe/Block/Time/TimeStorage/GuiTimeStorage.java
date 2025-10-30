package com.artiks.torcherinoCe.Block.Time.TimeStorage;

import com.artiks.torcherinoCe.Block.Core.ExampleGuiContainer;
import com.artiks.torcherinoCe.Gui.BarComponent;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiTimeStorage extends ExampleGuiContainer {

    private final TileTimeStorage tileEntity;

    public GuiTimeStorage(InventoryPlayer inventory, TileTimeStorage tileEntity) {
        super(new ContainerTimeStorage(inventory, tileEntity));
        this.tileEntity = tileEntity;
        setTexture("textures/gui/gui_time.png", 175, 167);
        BarComponent barComponent = new BarComponent(this, 1, 8, 16, 0, 19, 159, 16, "textures/gui/gui_component.png", BarComponent.SideEnum.LEFT);
        addBarComponent(barComponent);
    }
    @Override
    public void Tick() {
        setBarValue(1, tileEntity.getEnergyTime(), tileEntity.getMaxTimeStored());
        SetBarTooltips(1,tileEntity.getEnergyTime(), tileEntity.getMaxTimeStored(),true);
    }
}