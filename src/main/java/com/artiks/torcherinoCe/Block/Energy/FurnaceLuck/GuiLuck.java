package com.artiks.torcherinoCe.Block.Energy.FurnaceLuck;

import com.artiks.torcherinoCe.Block.Core.ExampleGuiContainer;
import com.artiks.torcherinoCe.Gui.*;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiLuck extends ExampleGuiContainer {
    private final TileLuck tile;
    TextureComponent textureComponent;

    public GuiLuck(InventoryPlayer inventory, TileLuck tileEntity) {
        super(new ContainerLuck(inventory, tileEntity));
        this.tile = tileEntity;
        setTexture("textures/gui/gui_furnace_luck.png", 212, 247);

        BarComponent bar = new BarComponent(this,1,46,84,0,148,129,5,"textures/gui/gui_component.png", BarComponent.SideEnum.LEFT);
        addBarComponent(bar);

        BarComponent energy_bar = new BarComponent(this,2,46,73,0,139,129,5,"textures/gui/gui_component.png", BarComponent.SideEnum.LEFT);
        addBarComponent(energy_bar);

        textureComponent = new TextureComponent(this,3,35,71,0,38,9,20,"textures/gui/gui_component.png");
        addTextureComponent(textureComponent);
    }

    @Override
    public void Tick() {
        setBarValue(1, tile.getProgress(),tile.getMaxProgress());
        setBarValue(2, tile.getEnergyStored(),tile.getMaxEnergyStorage());
        SetEnergyBarTooltips(2,tile.getEnergyStored(),tile.getMaxEnergyStorage(),tile.getEnergyPerTick(),true);
        textureComponent.setDrawing(tile.isWorking());
    }
}