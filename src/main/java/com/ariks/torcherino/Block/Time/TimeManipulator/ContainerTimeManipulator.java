package com.ariks.torcherino.Block.Time.TimeManipulator;

import com.ariks.torcherino.Block.Core.ExampleContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerTimeManipulator extends ExampleContainer {
    public ContainerTimeManipulator(InventoryPlayer inventoryPlayer, TileTimeManipulator tileEntity, EntityPlayer entityPlayer) {
        super(tileEntity);
        PlayerInventory(inventoryPlayer);
    }
}