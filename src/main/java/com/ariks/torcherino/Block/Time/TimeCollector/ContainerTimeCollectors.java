package com.ariks.torcherino.Block.Time.TimeCollector;

import com.ariks.torcherino.Block.Core.ExampleContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerTimeCollectors extends ExampleContainer {
    public ContainerTimeCollectors(InventoryPlayer inventoryPlayer, TileCollectors tileEntity, EntityPlayer entityPlayer) {
        super(tileEntity);
        PlayerInventory(inventoryPlayer);
    }
}