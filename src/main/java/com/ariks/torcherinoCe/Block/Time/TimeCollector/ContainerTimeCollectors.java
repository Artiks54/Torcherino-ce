package com.ariks.torcherinoCe.Block.Time.TimeCollector;

import com.ariks.torcherinoCe.Block.Core.ExampleContainer;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerTimeCollectors extends ExampleContainer {
    public ContainerTimeCollectors(InventoryPlayer inventoryPlayer, TileCollectors tileEntity) {
        super(tileEntity);
        PlayerInventory(inventoryPlayer);
    }
}