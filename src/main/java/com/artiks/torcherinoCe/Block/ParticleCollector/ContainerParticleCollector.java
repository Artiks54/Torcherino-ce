package com.artiks.torcherinoCe.Block.ParticleCollector;

import com.artiks.torcherinoCe.Block.Core.ExampleContainer;
import com.artiks.torcherinoCe.Block.Core.Slots.SlotOut;
import com.artiks.torcherinoCe.Block.Core.Slots.SlotItemExport;
import com.artiks.torcherinoCe.Block.Core.Slots.SlotUpgradeCount;
import com.artiks.torcherinoCe.Block.Core.Slots.SlotUpgradeSpeed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ContainerParticleCollector extends ExampleContainer {
    final int totalSlots = 6;
    public ContainerParticleCollector(InventoryPlayer inventoryPlayer, TileParticleCollector tileEntity) {
        super(tileEntity);
        this.addSlotToContainer(new SlotItemExport(tileEntity,0,5,7));
        this.addSlotToContainer(new SlotUpgradeCount(tileEntity,1,5,57));
        this.addSlotToContainer(new SlotUpgradeSpeed(tileEntity,2,5,32));
        this.addSlotToContainer(new SlotOut(tileEntity,3,155,7));
        this.addSlotToContainer(new SlotOut(tileEntity,4,155,32));
        this.addSlotToContainer(new SlotOut(tileEntity,5,155,57));
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