package com.artiks.torcherinoCe.Block.ItemStorage;

import com.artiks.torcherinoCe.Block.Core.ExampleContainer;
import com.artiks.torcherinoCe.Block.Core.Slots.SlotItemExport;
import com.artiks.torcherinoCe.Block.Core.Slots.SlotItemImport;
import com.artiks.torcherinoCe.Block.Core.Slots.SlotOut;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ContainerItemStorage extends ExampleContainer {
    final int totalSlots = 4;
    public ContainerItemStorage(InventoryPlayer inventoryPlayer, TileItemStorage tileEntity) {
        super(tileEntity);
        this.addSlotToContainer(new SlotInputItemStorage(tileEntity,0,134,6));
        this.addSlotToContainer(new SlotItemImport(tileEntity,1,154,6));
        this.addSlotToContainer(new SlotItemExport(tileEntity,2,154,55));
        this.addSlotToContainer(new SlotOut(tileEntity,3,134,55));
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
                if (!this.mergeItemStack(slotStack,totalSlots, 36+totalSlots, false)) {
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