package com.ariks.torcherinoCe.Block.Time.TimeEnergyCollector;

import com.ariks.torcherinoCe.Block.Core.ExampleGuiContainer;
import com.ariks.torcherinoCe.Gui.BarComponent;
import com.ariks.torcherinoCe.utility.EnergyFormat;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import java.util.Arrays;
import java.util.List;

@SideOnly(Side.CLIENT)
public class GuiEnergyTimeCollectors extends ExampleGuiContainer {
    private final TileEnergyCollectors tileentity;

    public GuiEnergyTimeCollectors(InventoryPlayer inventory, TileEnergyCollectors tileEntity) {
        super(new ContainerTimeEnergyCollectors(inventory,tileEntity));
        this.tileentity = tileEntity;
        setTexture("textures/gui/gui_energy_time.png", 175, 167);
        BarComponent EnergyBar = new BarComponent(this,1,8,16,0,0,159,16,"textures/gui/gui_component.png");
        BarComponent TimeBar = new BarComponent(this,2,8,49,0,19,159,16,"textures/gui/gui_component.png");
        addBarComponent(EnergyBar);
        addBarComponent(TimeBar);
    }
    @Override
    public void Tick() {
        //Energy
        String formattedValueMinEnergy = EnergyFormat.formatNumber(tileentity.getValue(2));
        String formattedValueMaxEnergy = EnergyFormat.formatNumber(tileentity.getMaxEnergyStorage());
        String formattedValueRf = EnergyFormat.formatNumber(tileentity.getEnergyPerTick());
        List<String> tooltipLines = Arrays.asList(
                formattedValueMinEnergy+ " / " + formattedValueMaxEnergy,
                "RF-Tick: " + formattedValueRf);
        setTooltipBarLines(1,tooltipLines);
        setBarValue(1, tileentity.getValue(2), tileentity.getMaxEnergyStorage());
        //Time
        String formattedValueMinTime = numberFormat.format(tileentity.getValue(1));
        String formattedValueMaxTime = numberFormat.format(tileentity.getMaxTimeStored());
        setTooltipBar(2,LS.StrTime+ " " +formattedValueMinTime+ " / " + formattedValueMaxTime);
        setBarValue(2, tileentity.getValue(1), tileentity.getMaxTimeStored());
    }
}