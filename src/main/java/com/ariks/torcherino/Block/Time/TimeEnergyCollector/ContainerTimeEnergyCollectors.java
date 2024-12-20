package com.ariks.torcherino.Block.Time.TimeEnergyCollector;

import com.ariks.torcherino.Block.Core.ExampleContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerTimeEnergyCollectors extends ExampleContainer {
    public ContainerTimeEnergyCollectors(InventoryPlayer inventoryPlayer, TileEnergyCollectors tileEntity, EntityPlayer entityPlayer) {
        super(tileEntity);
        PlayerInventory(inventoryPlayer);
    }
}