package com.ariks.torcherinoCe.Block.Time.TimeManipulator;

import com.ariks.torcherinoCe.Block.Core.ExampleContainer;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerTimeManipulator extends ExampleContainer {
    public ContainerTimeManipulator(InventoryPlayer inventoryPlayer, TileTimeManipulator tileEntity) {
        super(tileEntity);
        PlayerInventory(inventoryPlayer);
    }
}