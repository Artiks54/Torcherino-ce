package com.artiks.torcherinoCe.Block.Energy.Molecular.MolecularFarm;

import com.artiks.torcherinoCe.Block.Core.ExampleGuiContainer;
import com.artiks.torcherinoCe.Gui.BarComponent;
import com.artiks.torcherinoCe.utility.EnergyFormat;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import java.util.Arrays;
import java.util.List;

@SideOnly(Side.CLIENT)
public class GuiRfMolecularFarm extends ExampleGuiContainer {

    private final TileRfMolecularFarm tile;

    public GuiRfMolecularFarm(InventoryPlayer inventory, TileRfMolecularFarm tileEntity) {
        super(new ContainerRfMolecularFarm(inventory, tileEntity));
        this.tile = tileEntity;
        setTexture("textures/gui/molecular/farm.png", 233, 176);
        BarComponent barComponentEnergy = new BarComponent(this, 1, 10, 70, 0, 108,186,5,"textures/gui/gui_component.png", BarComponent.SideEnum.LEFT);
        addBarComponent(barComponentEnergy);
    }

    @Override
    public void Tick() {
        setBarValue(1,tile.getEnergyCollected(),tile.getTotalEnergyRequired());
        double percentage = (tile.getEnergyCollected() * 100.0) / tile.getTotalEnergyRequired();
        int progress = (int) Math.max(0, Math.min(100, percentage));
        List<String> tooltipLines = Arrays.asList(
                "Progress: " + progress + "%",
                "Recipes: " + tile.getActiveCount(),
                "RF-Tick: " + EnergyFormat.formatNumber(tile.getEnergyPerTick()),
                EnergyFormat.formatNumber(tile.getEnergyCollected()) + " / " + EnergyFormat.formatNumber(tile.getTotalEnergyRequired())
                );
        setTooltipBarList(1,tooltipLines);
    }
}