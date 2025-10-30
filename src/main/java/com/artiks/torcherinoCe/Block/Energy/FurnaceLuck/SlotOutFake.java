package com.artiks.torcherinoCe.Block.Energy.FurnaceLuck;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class SlotOutFake extends Slot {
    public SlotOutFake(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }
    @Override
    public boolean isItemValid(@NotNull ItemStack stack) {
        return false;
    }

    @Override
    public boolean canTakeStack(@NotNull EntityPlayer playerIn) {
        return false;
    }
    @Override
    public int getItemStackLimit(@NotNull ItemStack itemStack) {
        return Integer.MAX_VALUE;
    }
    @Override
    public int getSlotStackLimit() {
        return Integer.MAX_VALUE;
    }
}
