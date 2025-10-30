package com.artiks.torcherinoCe.Block.Energy.Molecular;

import com.artiks.torcherinoCe.Block.Core.ExampleContainer;
import com.artiks.torcherinoCe.Block.Core.Slots.SlotItemExport;
import com.artiks.torcherinoCe.Block.Core.Slots.SlotItemImport;
import com.artiks.torcherinoCe.Block.Core.Slots.SlotOut;
import com.artiks.torcherinoCe.Block.Core.Slots.inputSlot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ContainerRfMolecular extends ExampleContainer {
    final int totalSlots = 4;

    public ContainerRfMolecular(InventoryPlayer inventoryPlayer, TileRfMolecular tileEntity) {
        super(tileEntity);
        this.addSlotToContainer(new inputSlot(tileEntity,0,6,6));
        this.addSlotToContainer(new SlotItemImport(tileEntity,1,211,30));
        this.addSlotToContainer(new SlotItemExport(tileEntity,2,211,5));
        this.addSlotToContainer(new SlotOut(tileEntity,3,6,55));
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 23 + j * 18, 87 + i * 18));
            }
        }
        for (int k = 0; k < 9; ++k) {
            this.addSlotToContainer(new Slot(inventoryPlayer, k, 23 + k * 18, 145));
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
                if (!this.mergeItemStack(slotStack, 0, 3, false)) {
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