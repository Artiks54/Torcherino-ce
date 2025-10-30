package com.artiks.torcherinoCe.Block.Energy.FurnaceLuck;

import com.artiks.torcherinoCe.Block.Core.ExampleContainer;
import com.artiks.torcherinoCe.Block.Core.Slots.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ContainerLuck extends ExampleContainer {

    final int totalSlots = 58;

    public ContainerLuck(InventoryPlayer inventoryPlayer, TileLuck tileEntity) {
        super(tileEntity);
// Upgrade Slots (0-3)
        this.addSlotToContainer(new SlotEnergyModule(tileEntity,0,181,15));
        this.addSlotToContainer(new SlotItemImport(tileEntity,1,181,33));
        this.addSlotToContainer(new SlotItemExport(tileEntity,2,181,51));
        this.addSlotToContainer(new SlotFortuneModule(tileEntity,3,15,73));
// Input Slots (4-30)
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new inputSlot(tileEntity, 4 + j + i * 9, 15 + j * 18, 15 + i * 18));
            }
        }
// Output Slots (31-57)
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new SlotOutFake(tileEntity, 31 + j + i * 9, 15 + j * 18, 95 + i * 18));
            }
        }
//InventoryPlayer
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 15 + j * 18, 165 + i * 18));
            }
        }
        for (int k = 0; k < 9; ++k) {
            this.addSlotToContainer(new Slot(inventoryPlayer, k, 15 + k * 18, 223));
        }
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
                if (!this.mergeItemStack(slotStack, 0, totalSlots, false)) {
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