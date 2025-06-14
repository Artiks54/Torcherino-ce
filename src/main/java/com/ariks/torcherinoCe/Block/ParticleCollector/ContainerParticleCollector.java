package com.ariks.torcherinoCe.Block.ParticleCollector;

import com.ariks.torcherinoCe.Block.Core.ExampleContainer;
import com.ariks.torcherinoCe.Block.Core.SlotOut;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ContainerParticleCollector extends ExampleContainer {
    public ContainerParticleCollector(InventoryPlayer inventoryPlayer, TileParticleCollector tileEntity) {
        super(tileEntity);
        this.addSlotToContainer(new SlotUpgradeCount(tileEntity,0,70,2,tileEntity));
        this.addSlotToContainer(new SlotUpgradeSpeed(tileEntity,1,90,2,tileEntity));
        this.addSlotToContainer(new SlotOut(tileEntity,2,71,31));
        this.addSlotToContainer(new SlotOut(tileEntity,3,89,31));
        this.addSlotToContainer(new SlotOut(tileEntity,4,71,49));
        this.addSlotToContainer(new SlotOut(tileEntity,5,89,49));
        PlayerInventory(inventoryPlayer);
    }
    @Override
    public @NotNull ItemStack transferStackInSlot(@NotNull EntityPlayer player, int slotIndex) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(slotIndex);
        if (slot != null && slot.getHasStack()) {
            ItemStack slotStack = slot.getStack();
            itemstack = slotStack.copy();
            if (slotIndex < 6) {
                if (!this.mergeItemStack(slotStack,6, 36+6, false)) {
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