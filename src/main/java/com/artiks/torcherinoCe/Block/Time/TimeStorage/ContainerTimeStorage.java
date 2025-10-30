package com.artiks.torcherinoCe.Block.Time.TimeStorage;

import com.artiks.torcherinoCe.Block.Core.ExampleContainer;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerTimeStorage extends ExampleContainer {
    public ContainerTimeStorage(InventoryPlayer inventoryPlayer, TileTimeStorage tileEntity) {
        super(tileEntity);
        PlayerInventory(inventoryPlayer);
    }
}