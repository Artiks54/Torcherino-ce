package com.ariks.torcherino.Block.TimeCollector;

import com.ariks.torcherino.Block.ExampleContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerTimeCollectors extends ExampleContainer {
    public ContainerTimeCollectors(InventoryPlayer inventoryPlayer, TileCollectors tileEntity, EntityPlayer entityPlayer) {
        super(tileEntity);
        PlayerInventory(inventoryPlayer);
    }
}