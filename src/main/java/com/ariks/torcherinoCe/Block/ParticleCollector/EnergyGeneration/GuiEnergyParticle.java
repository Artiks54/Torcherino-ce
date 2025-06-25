package com.ariks.torcherinoCe.Block.ParticleCollector.EnergyGeneration;

import com.ariks.torcherinoCe.Gui.BarComponent;
import com.ariks.torcherinoCe.Block.Core.ExampleGuiContainer;
import com.ariks.torcherinoCe.utility.EnergyFormat;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import java.util.Arrays;
import java.util.List;

@SideOnly(Side.CLIENT)
public class GuiEnergyParticle extends ExampleGuiContainer {

    private final TileEnergyParticle tileEntity;
    public GuiEnergyParticle(InventoryPlayer inventoryPlayer, TileEnergyParticle tileEntity) {
        super(new ContainerEnergyParticle(inventoryPlayer, tileEntity));
        this.tileEntity = tileEntity;
        setTexture("textures/gui/gui_energy.png", 175, 167);

        BarComponent energy = new BarComponent(this,1,9,7,197,0,16,61,"textures/gui/gui_component.png");
        energy.setSideDirection("up");
        addBarComponent(energy);

        BarComponent progress = new BarComponent(this,2,32,29,43,53,24,16,"textures/gui/gui_component.png");
        progress.setSideDirection("left");
        addBarComponent(progress);
    }
    @Override
    public void Tick() {
        String formattedValueMin = EnergyFormat.formatNumber(tileEntity.getValue(2));
        String formattedValueMax = EnergyFormat.formatNumber(tileEntity.getMaxEnergyStorage());
        String formattedValueRf = EnergyFormat.formatNumber(tileEntity.getEnergyPerTick());
        List<String> tooltipLines = Arrays.asList(
                formattedValueMin+ " / " + formattedValueMax,
                "RF-Tick: " + formattedValueRf);
        setTooltipBarLines(1,tooltipLines);
        setBarValue(1,tileEntity.getValue(2),tileEntity.getMaxEnergyStorage());
        setBarValue(2,tileEntity.getValue(1),tileEntity.getMaxProgress());
    }
}