package com.ariks.torcherinoCe.Block.Torcherino;

import com.ariks.torcherinoCe.Block.Core.ExampleContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerTorcherino extends ExampleContainer {
    public ContainerTorcherino(InventoryPlayer inventoryPlayer, TileTorcherinoBase tileEntity, EntityPlayer entityPlayer) {
        super(tileEntity);
    }
}