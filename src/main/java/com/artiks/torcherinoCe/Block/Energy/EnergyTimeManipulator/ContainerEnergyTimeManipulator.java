package com.artiks.torcherinoCe.Block.Energy.EnergyTimeManipulator;

import com.artiks.torcherinoCe.Block.Core.ExampleContainer;
import com.artiks.torcherinoCe.Block.Core.Slots.SlotEnergyModule;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ContainerEnergyTimeManipulator extends ExampleContainer {
    final int totalSlots = 1;
    public ContainerEnergyTimeManipulator(InventoryPlayer inventoryPlayer, TileEnergyTimeManipulator tileEntity) {
        super(tileEntity);
        this.addSlotToContainer(new SlotEnergyModule(tileEntity, 0, 180, 5));
        PlayerInventory(inventoryPlayer);
    }
    @Override
    public @NotNull ItemStack transferStackInSlot(@NotNull EntityPlayer player, int slotIndex) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(slotIndex);
        if (slot != null && slot.getHasStack()) {
            ItemStack slotStack = slot.getStack();
            itemstack = slotStack.copy();
            if (slotIndex < totalSlots) {
                if (!this.mergeItemStack(slotStack, totalSlots, 36 + totalSlots, false)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (!this.mergeItemStack(slotStack, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            }
            if (slotStack.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
            if (slotStack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTake(player, slotStack);
        }
        return itemstack;
    }
}