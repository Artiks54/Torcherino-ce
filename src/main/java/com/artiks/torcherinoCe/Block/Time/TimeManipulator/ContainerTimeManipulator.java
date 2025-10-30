package com.artiks.torcherinoCe.Block.Time.TimeManipulator;

import com.artiks.torcherinoCe.Block.Core.ExampleContainer;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerTimeManipulator extends ExampleContainer {
    public ContainerTimeManipulator(InventoryPlayer inventoryPlayer, TileTimeManipulator tileEntity) {
        super(tileEntity);
        PlayerInventory(inventoryPlayer);
    }
}