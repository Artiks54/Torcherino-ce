package com.ariks.torcherino.Block.Aceleration;

import com.ariks.torcherino.Block.ExampleContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerAceleration extends ExampleContainer {
    private int TimeStorage;
    public ContainerAceleration(InventoryPlayer inventoryPlayer, TileAcceleration tileEntity, EntityPlayer entityPlayer) {
        super(tileEntity,inventoryPlayer,true);
    }
}