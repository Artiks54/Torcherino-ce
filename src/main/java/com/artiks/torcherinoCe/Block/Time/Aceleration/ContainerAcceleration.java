package com.artiks.torcherinoCe.Block.Time.Aceleration;

import com.artiks.torcherinoCe.Block.Core.ExampleContainer;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerAcceleration extends ExampleContainer {

    public ContainerAcceleration(InventoryPlayer inventoryPlayer, TileAcceleration tileEntity) {
        super(tileEntity);
        PlayerInventory(inventoryPlayer);
    }
}