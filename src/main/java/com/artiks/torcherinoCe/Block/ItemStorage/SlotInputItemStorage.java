package com.artiks.torcherinoCe.Block.ItemStorage;

import com.artiks.torcherinoCe.Items.ItemExport;
import com.artiks.torcherinoCe.Items.ItemImport;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class SlotInputItemStorage extends Slot {
    private final IInventory tileInventory;

    public SlotInputItemStorage(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
        this.tileInventory = inventoryIn;
    }

    @Override
    public boolean isItemValid(@NotNull ItemStack stack) {
        if (stack.getItem() instanceof ItemImport)return false;
        if (stack.getItem() instanceof ItemExport)return false;
        ItemStack outputStack = tileInventory.getStackInSlot(3);
        if (outputStack.isEmpty()) {
            return true;
        }
        return ItemStack.areItemsEqual(outputStack, stack) && ItemStack.areItemStackTagsEqual(outputStack, stack);
    }

    @Override
    public int getItemStackLimit(@NotNull ItemStack itemStack) {
        return 64;
    }

    @Override
    public int getSlotStackLimit() {
        return 64;
    }
}