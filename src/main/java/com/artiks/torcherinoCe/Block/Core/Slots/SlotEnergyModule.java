package com.artiks.torcherinoCe.Block.Core.Slots;

import com.artiks.torcherinoCe.Register.RegistryItems;
import com.artiks.torcherinoCe.utility.IEnergyTile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class SlotEnergyModule extends Slot {

    public SlotEnergyModule(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }
    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.getItem() == RegistryItems.EnergyModule;
    }
    @Override
    public int getItemStackLimit(@NotNull ItemStack itemStack) {
        return 1;
    }
    @Override
    public int getSlotStackLimit() {
        return 1;
    }
    @Override
    public void onSlotChanged() {
        super.onSlotChanged();
        if (this.inventory instanceof IEnergyTile energyTile) {
            ItemStack stack = this.getStack();
            if (!stack.isEmpty() && stack.getItem() == RegistryItems.EnergyModule) {
                energyTile.setUseEnergy(false);
                energyTile.setEnergyStorage(energyTile.getMaxEnergyStorage());
            } else {
                energyTile.setUseEnergy(true);
                energyTile.setEnergyStorage(0);
            }
        }
    }
    @Override
    public @NotNull ItemStack onTake(@NotNull EntityPlayer thePlayer, @NotNull ItemStack stack) {
        if (this.inventory instanceof IEnergyTile energyTile) {
            if (!stack.isEmpty() && stack.getItem() == RegistryItems.EnergyModule) {
                energyTile.setUseEnergy(false);
                energyTile.setEnergyStorage(energyTile.getMaxEnergyStorage());
            } else {
                energyTile.setUseEnergy(true);
                energyTile.setEnergyStorage(0);
            }
        }
        return super.onTake(thePlayer, stack);
    }
}
