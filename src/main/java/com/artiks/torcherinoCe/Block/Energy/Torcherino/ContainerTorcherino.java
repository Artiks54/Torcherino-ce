package com.artiks.torcherinoCe.Block.Energy.Torcherino;

import com.artiks.torcherinoCe.Block.Core.ExampleContainer;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerTorcherino extends ExampleContainer {
    public ContainerTorcherino(@SuppressWarnings("unused") InventoryPlayer inventoryPlayer, TileTorcherinoBase tileEntity) {
        super(tileEntity);
    }
}