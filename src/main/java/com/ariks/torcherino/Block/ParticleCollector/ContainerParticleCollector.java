package com.ariks.torcherino.Block.ParticleCollector;

import com.ariks.torcherino.Block.ExampleContainer;
import com.ariks.torcherino.util.SlotOut;
import com.ariks.torcherino.util.SlotUpgrade;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerParticleCollector extends ExampleContainer {
    public ContainerParticleCollector(InventoryPlayer inventoryPlayer, TileParticleCollector tileEntity, EntityPlayer entityPlayer) {
        super(tileEntity);
        this.addSlotToContainer(new SlotOut(tileEntity, 0, 148, 31));
        this.addSlotToContainer(new SlotUpgrade(tileEntity, 1, 148, 2));
        PlayerInventory(inventoryPlayer);
    }
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(slotIndex);
        if (slot != null && slot.getHasStack()) {
            ItemStack slotStack = slot.getStack();
            itemstack = slotStack.copy();
            if (slotIndex == 0 || slotIndex == 1) {
                if (!this.mergeItemStack(slotStack,2, 38, false)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (!this.mergeItemStack(slotStack, 1, 2, false)) {
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