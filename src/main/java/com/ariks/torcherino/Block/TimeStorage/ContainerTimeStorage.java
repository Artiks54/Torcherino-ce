package com.ariks.torcherino.Block.TimeStorage;

import com.ariks.torcherino.Block.ExampleContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerTimeStorage extends ExampleContainer {
    public ContainerTimeStorage(InventoryPlayer inventoryPlayer, TileTimeStorage tileEntity,EntityPlayer entityPlayer) {
        super(tileEntity);
    }
}