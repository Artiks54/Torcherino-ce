package com.ariks.torcherino.Block.ParticleCollector;

import com.ariks.torcherino.Block.ExampleContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ContainerParticleCollector extends ExampleContainer  {
    public ContainerParticleCollector(InventoryPlayer inventoryPlayer, TileParticleCollector tileEntity, EntityPlayer entityPlayer) {
        super(tileEntity,inventoryPlayer,true);
        this.addSlotToContainer(new Slot(tileEntity, 0, 80, 31) {
            @Override
            public boolean isItemValid(@NotNull ItemStack stack) {
                return false;
            }
        });
    }
}