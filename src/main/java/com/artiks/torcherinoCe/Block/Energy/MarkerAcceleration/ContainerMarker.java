package com.artiks.torcherinoCe.Block.Energy.MarkerAcceleration;

import com.artiks.torcherinoCe.Block.Core.ExampleContainer;
import com.artiks.torcherinoCe.Block.Core.Slots.SlotEnergyModule;
import com.artiks.torcherinoCe.Block.Core.Slots.UpgradeSlot;
import com.artiks.torcherinoCe.Block.Core.Slots.slotMarker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ContainerMarker extends ExampleContainer {
    final int totalSlots = 10;
    public ContainerMarker(InventoryPlayer inventoryPlayer, TileMarker tileEntity) {
        super(tileEntity);
        this.addSlotToContainer(new slotMarker(tileEntity,0,8,54));
        this.addSlotToContainer(new slotMarker(tileEntity,1,26,54));
        this.addSlotToContainer(new slotMarker(tileEntity,2,44,54));
        this.addSlotToContainer(new slotMarker(tileEntity,3,62,54));
        this.addSlotToContainer(new slotMarker(tileEntity,4,80,54));
        this.addSlotToContainer(new slotMarker(tileEntity,5,98,54));
        this.addSlotToContainer(new slotMarker(tileEntity,6,116,54));
        this.addSlotToContainer(new slotMarker(tileEntity,7,134,54));
        this.addSlotToContainer(new UpgradeSlot(tileEntity,8,152,54));
        this.addSlotToContainer(new SlotEnergyModule(tileEntity, 9, 180, 5));
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
