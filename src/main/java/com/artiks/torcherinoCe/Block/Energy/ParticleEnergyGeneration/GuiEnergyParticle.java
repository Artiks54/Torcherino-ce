package com.artiks.torcherinoCe.Block.Energy.ParticleEnergyGeneration;

import com.artiks.torcherinoCe.Gui.BarComponent;
import com.artiks.torcherinoCe.Block.Core.ExampleGuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiEnergyParticle extends ExampleGuiContainer {

    private final TileEnergyParticle tileEntity;
    public GuiEnergyParticle(InventoryPlayer inventoryPlayer, TileEnergyParticle tileEntity) {
        super(new ContainerEnergyParticle(inventoryPlayer, tileEntity));
        this.tileEntity = tileEntity;
        setTexture("textures/gui/gui_energy.png", 200, 167);
        BarComponent energy = new BarComponent(this,1,9,7,197,0,16,61,"textures/gui/gui_component.png", BarComponent.SideEnum.UP);
        addBarComponent(energy);
        BarComponent progress = new BarComponent(this,2,32,29,43,53,24,16,"textures/gui/gui_component.png", BarComponent.SideEnum.LEFT);
        addBarComponent(progress);
    }
    @Override
    public void Tick() {
        SetEnergyBarTooltips(1,tileEntity.getEnergyStored(),tileEntity.getMaxEnergyStorage(),tileEntity.getEnergyPerTick(),true);
        setBarValue(1,tileEntity.getEnergyStored(),tileEntity.getMaxEnergyStorage());
        setBarValue(2,tileEntity.getProgress(),tileEntity.getMaxProgress());
    }
}