package com.ariks.torcherino.Block.TimeManipulator;

import com.ariks.torcherino.Block.ExampleContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerTimeManipulator extends ExampleContainer {
    public ContainerTimeManipulator(InventoryPlayer inventoryPlayer, TileTimeManipulator tileEntity, EntityPlayer entityPlayer) {
        super(tileEntity,inventoryPlayer,true);
    }
}