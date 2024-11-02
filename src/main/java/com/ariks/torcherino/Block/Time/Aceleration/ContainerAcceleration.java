package com.ariks.torcherino.Block.Time.Aceleration;

import com.ariks.torcherino.Block.Core.ExampleContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerAcceleration extends ExampleContainer {

    public ContainerAcceleration(InventoryPlayer inventoryPlayer, TileAcceleration tileEntity, EntityPlayer entityPlayer) {
        super(tileEntity);
        PlayerInventory(inventoryPlayer);
    }
}