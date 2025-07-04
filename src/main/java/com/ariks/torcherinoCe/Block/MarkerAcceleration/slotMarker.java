package com.ariks.torcherinoCe.Block.MarkerAcceleration;

import com.ariks.torcherinoCe.Items.itemMarker;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class slotMarker extends Slot {
    public slotMarker(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }
    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.getItem() instanceof itemMarker;
    }
    @Override
    public int getItemStackLimit(@NotNull ItemStack itemStack) {
        return 1;
    }
    @Override
    public int getSlotStackLimit() {
        return 1;
    }
}
