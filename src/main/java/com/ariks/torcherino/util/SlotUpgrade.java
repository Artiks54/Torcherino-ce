package com.ariks.torcherino.util;

import com.ariks.torcherino.Items.ItemUpgrade;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotUpgrade extends Slot {
    public SlotUpgrade(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }
    @Override
    public boolean isItemValid(ItemStack stack) {
        if(stack.getItem() instanceof ItemUpgrade){
            return true;
        }
        return false;
    }
    @Override
    public int getSlotStackLimit() {
        return 1;
    }
}