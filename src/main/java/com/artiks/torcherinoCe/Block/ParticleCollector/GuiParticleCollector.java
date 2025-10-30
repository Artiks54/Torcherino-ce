package com.artiks.torcherinoCe.Block.ParticleCollector;

import com.artiks.torcherinoCe.Block.Core.ExampleGuiContainer;
import com.artiks.torcherinoCe.Gui.BarComponent;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiParticleCollector extends ExampleGuiContainer {
    private final TileParticleCollector tile;
    public GuiParticleCollector(InventoryPlayer inventory, TileParticleCollector tileEntity) {
        super(new ContainerParticleCollector(inventory, tileEntity));
        this.tile = tileEntity;
        setTexture("textures/gui/gui_particle_collector.png", 175, 167);
        BarComponent barComponent = new BarComponent(this,1,31,5,0,164,119,69,"textures/gui/gui_component.png", BarComponent.SideEnum.LEFT);
        addBarComponent(barComponent);
    }
    @Override
    public void Tick() {
        setBarValue(1,tile.getProgress(),tile.getMaxProgress());
        SetBarTooltips(1,tile.getProgress(),tile.getMaxProgress(),true);
    }
}