package com.artiks.torcherinoCe.Block.Core.Slots;

import com.artiks.torcherinoCe.Register.RegistryItems;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class inputSlot extends Slot {
    public inputSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }
    @Override
    public int getItemStackLimit(@NotNull ItemStack itemStack) {
        return 64;
    }
    @Override
    public int getSlotStackLimit() {
        return 64;
    }

    @Override
    public boolean isItemValid(@NotNull ItemStack stack) {
        if(stack.getItem() == RegistryItems.EnergyModule){
            return false;
        }
        if(stack.getItem() == RegistryItems.ImportModule){
            return false;
        }
        if(stack.getItem() == RegistryItems.ExportModule){
            return false;
        }
        if(stack.getItem() == RegistryItems.FortuneModule){
            return false;
        }
        if(stack.getItem() == RegistryItems.upgrade_gps){
            return false;
        }
        if(stack.getItem() == RegistryItems.upgrade_gps_2){
            return false;
        }
        if(stack.getItem() == RegistryItems.upgrade_count){
            return false;
        }
        if(stack.getItem() == RegistryItems.upgrade_speed){
            return false;
        }
        return super.isItemValid(stack);
    }
}
