package com.artiks.torcherinoCe.Block.Energy.CobbleGenerator;

import com.artiks.torcherinoCe.Block.Core.ExampleGuiContainer;
import com.artiks.torcherinoCe.Gui.BarComponent;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiCobbleGenerator extends ExampleGuiContainer {

    private final TileCobbleGenerator tile;

    public GuiCobbleGenerator(InventoryPlayer inventory, TileCobbleGenerator tileEntity) {
        super(new ContainerCobbleGenerator(inventory, tileEntity));
        this.tile = tileEntity;
        setTexture("textures/gui/molecular/cobble_gen.png", 236, 176);
        BarComponent barComponentEnergy = new BarComponent(this, 1, 10, 68, 0, 99,186,5,"textures/gui/gui_component.png", BarComponent.SideEnum.LEFT);
        addBarComponent(barComponentEnergy);
    }

    @Override
    public void Tick() {
        //Energy
        setBarValue(1,tile.getEnergyStored(),tile.getMaxEnergyStorage());
        SetEnergyBarTooltips(1,tile.getEnergyStored(),tile.getMaxEnergyStorage(),tile.getEnergyPerTick(),true);
    }
}