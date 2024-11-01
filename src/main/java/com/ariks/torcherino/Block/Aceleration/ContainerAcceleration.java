package com.ariks.torcherino.Block.Aceleration;

import com.ariks.torcherino.Block.ExampleContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerAcceleration extends ExampleContainer {

    public ContainerAcceleration(InventoryPlayer inventoryPlayer, TileAcceleration tileEntity, EntityPlayer entityPlayer) {
        super(tileEntity);
        PlayerInventory(inventoryPlayer);
    }
}