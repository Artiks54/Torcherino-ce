package com.artiks.torcherinoCe.Block.Energy.TimeEnergyCollector;

import com.artiks.torcherinoCe.Block.Core.ExampleGuiContainer;
import com.artiks.torcherinoCe.Gui.BarComponent;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiEnergyTimeCollectors extends ExampleGuiContainer {
    private final TileEnergyCollectors tileEntity;

    public GuiEnergyTimeCollectors(InventoryPlayer inventory, TileEnergyCollectors tileEntity) {
        super(new ContainerTimeEnergyCollectors(inventory,tileEntity));
        this.tileEntity = tileEntity;
        setTexture("textures/gui/gui_energy_time.png", 175, 167);
        BarComponent EnergyBar = new BarComponent(this,1,8,16,0,0,159,16,"textures/gui/gui_component.png", BarComponent.SideEnum.LEFT);
        BarComponent TimeBar = new BarComponent(this,2,8,49,0,19,159,16,"textures/gui/gui_component.png", BarComponent.SideEnum.LEFT);
        addBarComponent(EnergyBar);
        addBarComponent(TimeBar);
    }
    @Override
    public void Tick() {
        //Energy
        SetEnergyBarTooltips(1,tileEntity.getEnergyStorage(),tileEntity.getMaxEnergyStorage(),tileEntity.getEnergyPerTick(),true);
        setBarValue(1, tileEntity.getEnergyStorage(), tileEntity.getMaxEnergyStorage());
        //Time
        SetBarTooltips(2,tileEntity.getEnergyTime(),tileEntity.getMaxTimeStored(),true);
        setBarValue(2, tileEntity.getEnergyTime(), tileEntity.getMaxTimeStored());
    }
}