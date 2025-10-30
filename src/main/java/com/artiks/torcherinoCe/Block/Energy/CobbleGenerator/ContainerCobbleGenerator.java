package com.artiks.torcherinoCe.Block.Energy.CobbleGenerator;

import com.artiks.torcherinoCe.Block.Core.ExampleContainer;
import com.artiks.torcherinoCe.Block.Core.Slots.SlotItemExport;
import com.artiks.torcherinoCe.Block.Core.Slots.SlotOut;
import com.artiks.torcherinoCe.Block.Core.Slots.SlotUpgradeGen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ContainerCobbleGenerator extends ExampleContainer {

    final int totalSlots = 32;

    public ContainerCobbleGenerator(InventoryPlayer inventoryPlayer, TileCobbleGenerator tileEntity) {
        super(tileEntity);
//Export id 0
        this.addSlotToContainer(new SlotItemExport(tileEntity, 0, 215, 5));
//Upgrade id 1
        this.addSlotToContainer(new SlotUpgradeGen(tileEntity, 1, 215, 30));
// Output id 2-31
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 10; ++j) {
                this.addSlotToContainer(new SlotOut(tileEntity, j + i * 10 + 2,14 + j * 18, 8 + i * 18));
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
                if (!this.mergeItemStack(slotStack, 0, 2, false)) {
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