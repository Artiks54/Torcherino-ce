package com.ariks.torcherinoCe.Block.Time.TimeEnergyCollector;

import com.ariks.torcherinoCe.Block.Core.ExampleContainer;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerTimeEnergyCollectors extends ExampleContainer {
    public ContainerTimeEnergyCollectors(InventoryPlayer inventoryPlayer, TileEnergyCollectors tileEntity) {
        super(tileEntity);
        PlayerInventory(inventoryPlayer);
    }
}