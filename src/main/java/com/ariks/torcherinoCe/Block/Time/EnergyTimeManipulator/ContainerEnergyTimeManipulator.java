package com.ariks.torcherinoCe.Block.Time.EnergyTimeManipulator;

import com.ariks.torcherinoCe.Block.Core.ExampleContainer;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerEnergyTimeManipulator extends ExampleContainer {
    public ContainerEnergyTimeManipulator(InventoryPlayer inventoryPlayer, TileEnergyTimeManipulator tileEntity) {
        super(tileEntity);
        PlayerInventory(inventoryPlayer);
    }
}