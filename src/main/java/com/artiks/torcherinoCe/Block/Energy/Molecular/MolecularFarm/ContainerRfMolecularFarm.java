package com.artiks.torcherinoCe.Block.Energy.Molecular.MolecularFarm;

import com.artiks.torcherinoCe.Block.Core.ExampleContainer;
import com.artiks.torcherinoCe.Block.Core.Slots.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ContainerRfMolecularFarm extends ExampleContainer {
    final int totalSlots = 25;

    public ContainerRfMolecularFarm(InventoryPlayer inventoryPlayer, TileRfMolecularFarm tileEntity) {
        super(tileEntity);
//Input id 0-11
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 4; ++j) {
                this.addSlotToContainer(new inputSlot(tileEntity, j + i * 4, 10 + j * 18, 12 + i * 18));
            }
        }
//Export id 12
        this.addSlotToContainer(new SlotItemExport(tileEntity,12,212,5));
//Output id 13-24
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 4; ++j) {
                this.addSlotToContainer(new SlotOut(tileEntity, j + i * 4 + 13, 126 + j * 18, 12 + i * 18));
            }
        }
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 23 + j * 18, 94 + i * 18));
            }
        }
        for (int k = 0; k < 9; ++k) {
            this.addSlotToContainer(new Slot(inventoryPlayer, k, 23 + k * 18, 152));
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
                if (!this.mergeItemStack(slotStack, 0, 13, false)) {
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