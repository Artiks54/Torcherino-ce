package com.ariks.torcherino.Block.Time.TimeStorage;

import com.ariks.torcherino.Block.Core.ExampleContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerTimeStorage extends ExampleContainer {
    public ContainerTimeStorage(InventoryPlayer inventoryPlayer, TileTimeStorage tileEntity,EntityPlayer entityPlayer) {
        super(tileEntity);
        PlayerInventory(inventoryPlayer);
    }
}