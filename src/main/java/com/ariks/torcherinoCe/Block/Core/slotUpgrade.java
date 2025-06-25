package com.ariks.torcherinoCe.Block.Core;

import com.ariks.torcherinoCe.Register.RegistryItems;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class slotUpgrade extends Slot {
    public slotUpgrade(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }
    @Override
    public boolean isItemValid(ItemStack stack) {
        if(stack.getItem() == RegistryItems.upgrade_gps){
            return true;
        }
        if(stack.getItem() == RegistryItems.upgrade_gps_2){
            return true;
        }
        return false;
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
