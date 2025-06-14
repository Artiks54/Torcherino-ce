package com.ariks.torcherinoCe.Block.Time.TimeEnergyCollector;

import com.ariks.torcherinoCe.Block.Core.ExampleGuiContainer;
import com.ariks.torcherinoCe.Gui.BarComponent;
import com.ariks.torcherinoCe.utility.EnergyFormat;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiEnergyTimeCollectors extends ExampleGuiContainer {
    private final TileEnergyCollectors tile;

    public GuiEnergyTimeCollectors(InventoryPlayer inventory, TileEnergyCollectors tileEntity) {
        super(new ContainerTimeEnergyCollectors(inventory,tileEntity));
        this.tile = tileEntity;
        setTexture("textures/gui/gui_energy_time.png", 175, 167);
        BarComponent EnergyBar = new BarComponent(this,1,8,16,0,0,159,16,"textures/gui/gui_component.png");
        BarComponent TimeBar = new BarComponent(this,2,8,49,0,19,159,16,"textures/gui/gui_component.png");
        addBarComponent(EnergyBar);
        addBarComponent(TimeBar);
    }
    @Override
    public void Tick() {
        //Energy
        String formattedValueMinEnergy = EnergyFormat.formatNumber(tile.getValue(2));
        String formattedValueMaxEnergy = EnergyFormat.formatNumber(tile.getMaxEnergyStorage());
        setTooltipBar(1,formattedValueMinEnergy+ " / " + formattedValueMaxEnergy);
        setBarValue(1,tile.getValue(2),tile.getMaxEnergyStorage());
        //Time
        String formattedValueMinTime = numberFormat.format(tile.getValue(1));
        String formattedValueMaxTime = numberFormat.format(tile.getMaxTimeStored());
        setTooltipBar(2,LS.StrTime+ " " +formattedValueMinTime+ " / " + formattedValueMaxTime);
        setBarValue(2,tile.getValue(1),tile.getMaxTimeStored());
    }
}