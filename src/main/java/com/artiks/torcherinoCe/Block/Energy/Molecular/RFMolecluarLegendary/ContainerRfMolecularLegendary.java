package com.artiks.torcherinoCe.Block.Energy.Molecular.RFMolecluarLegendary;

import com.artiks.torcherinoCe.Block.Core.ExampleContainer;
import com.artiks.torcherinoCe.Block.Core.Slots.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ContainerRfMolecularLegendary extends ExampleContainer {
    final int totalSlots = 27;

    public ContainerRfMolecularLegendary(InventoryPlayer inventoryPlayer, TileRfMolecularLegendary tileEntity) {
        super(tileEntity);
//Input id 0-11
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 4; ++j) {
                this.addSlotToContainer(new inputSlot(tileEntity, j + i * 4, 10 + j * 18, 12 + i * 18));
            }
        }
//Fortune id 12,13,14
        this.addSlotToContainer(new SlotFortuneModule(tileEntity,12,95,30));
        this.addSlotToContainer(new SlotItemImport(tileEntity,13,212,30));
        this.addSlotToContainer(new SlotItemExport(tileEntity,14,212,5));
//Output id 15-26
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 4; ++j) {
                this.addSlotToContainer(new SlotOut(tileEntity, j + i * 4 + 15, 126 + j * 18, 12 + i * 18));
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
                if (!this.mergeItemStack(slotStack, 0, 15, false)) {
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