package com.ariks.torcherino.util;

import com.ariks.torcherino.Register.RegistryItems;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotUpgrade extends Slot {
    public SlotUpgrade(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }
    @Override
    public boolean isItemValid(ItemStack stack) {
        if(stack.getItem() == RegistryItems.upgrade_kit6){
            return true;
        }
        if(stack.getItem() == RegistryItems.upgrade_kit5){
            return true;
        }
        if(stack.getItem() == RegistryItems.upgrade_kit4){
            return true;
        }
        if(stack.getItem() == RegistryItems.upgrade_kit3){
            return true;
        }
        if(stack.getItem() == RegistryItems.upgrade_kit2){
            return true;
        }
        if(stack.getItem() == RegistryItems.upgrade_kit1){
            return true;
        }
        return false;
    }
    @Override
    public int getSlotStackLimit() {
        return 1;
    }
}