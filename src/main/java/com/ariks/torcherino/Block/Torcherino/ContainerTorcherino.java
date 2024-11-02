package com.ariks.torcherino.Block.Torcherino;

import com.ariks.torcherino.Block.Core.ExampleContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerTorcherino extends ExampleContainer {
    public ContainerTorcherino(InventoryPlayer inventoryPlayer, TileTorcherinoBase tileEntity, EntityPlayer entityPlayer) {
        super(tileEntity);
    }
}