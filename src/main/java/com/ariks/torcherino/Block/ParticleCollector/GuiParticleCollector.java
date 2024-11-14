package com.ariks.torcherino.Block.ParticleCollector;

import com.ariks.torcherino.Block.Core.ExampleGuiContainer;
import com.ariks.torcherino.Gui.BarComponent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiParticleCollector extends ExampleGuiContainer {
    private final TileParticleCollector tile;
    public GuiParticleCollector(InventoryPlayer inventory, TileParticleCollector tileEntity, EntityPlayer player) {
        super(new ContainerParticleCollector(inventory, tileEntity, player));
        this.tile = tileEntity;
        setTexture("textures/gui/gui_particle_collector.png", 175, 167);
        //bar left
        BarComponent barComponent = new BarComponent(this,1,28,27,0,38,40,42,"textures/gui/gui_component.png");
        addBarComponent(barComponent);
        barComponent.setSideDirection("left");
        //bar right
        BarComponent barComponent1 = new BarComponent(this,2,108,27,0,82,40,42,"textures/gui/gui_component.png");
        barComponent1.setSideDirection("right");
        addBarComponent(barComponent1);
        //bar down
        BarComponent barComponent2 = new BarComponent(this,3,71,19,43,42,34,9,"textures/gui/gui_component.png");
        barComponent2.setSideDirection("down");
        addBarComponent(barComponent2);
    }
    @Override
    public void Tick() {
        int progress = tile.getValue(1);
        int maxProgress = tile.getValue(2);
        setBarValue(1,progress,maxProgress);
        setBarValue(2,progress,maxProgress);
        setBarValue(3,progress,maxProgress);
    }
}