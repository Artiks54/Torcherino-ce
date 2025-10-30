package com.artiks.torcherinoCe.Block.Core.Slots;

import com.artiks.torcherinoCe.Block.Energy.MarkerAcceleration.TileMarker;
import com.artiks.torcherinoCe.Register.RegistryItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class UpgradeSlot extends Slot {

    public UpgradeSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }
    @Override
    public void onSlotChanged() {
        super.onSlotChanged();
        if(inventory instanceof TileMarker) {
            ItemStack stack = this.getStack();
            if (stack.getItem() == RegistryItems.upgrade_gps) {
                ((TileMarker)inventory).setMaxSpeed(9);
            } else if (stack.getItem() == RegistryItems.upgrade_gps_2) {
                ((TileMarker)inventory).setMaxSpeed(81);
            } else {
                ((TileMarker)inventory).setMaxSpeed(1);
            }
        }
    }

    @Override
    public @NotNull ItemStack onTake(@NotNull EntityPlayer thePlayer, @NotNull ItemStack stack) {
        if(inventory instanceof TileMarker) {
            if (stack.getItem() == RegistryItems.upgrade_gps) {
                ((TileMarker) inventory).setMaxSpeed(9);
            } else if (stack.getItem() == RegistryItems.upgrade_gps_2) {
                ((TileMarker) inventory).setMaxSpeed(81);
            } else {
                ((TileMarker) inventory).setMaxSpeed(1);
            }
        }
        return super.onTake(thePlayer, stack);
    }

    @Override
    public int getItemStackLimit(@NotNull ItemStack stack) {
        return 1;
    }

    @Override
    public int getSlotStackLimit() {
        return 1;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.getItem() == RegistryItems.upgrade_gps || stack.getItem() == RegistryItems.upgrade_gps_2;
    }
}