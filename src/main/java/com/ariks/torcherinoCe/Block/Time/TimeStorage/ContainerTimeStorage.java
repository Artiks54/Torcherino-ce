package com.ariks.torcherinoCe.Block.Time.TimeStorage;

import com.ariks.torcherinoCe.Block.Core.ExampleContainer;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerTimeStorage extends ExampleContainer {
    public ContainerTimeStorage(InventoryPlayer inventoryPlayer, TileTimeStorage tileEntity) {
        super(tileEntity);
        PlayerInventory(inventoryPlayer);
    }
}