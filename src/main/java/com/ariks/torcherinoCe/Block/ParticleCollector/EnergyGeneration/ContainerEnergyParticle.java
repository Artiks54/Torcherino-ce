package com.ariks.torcherinoCe.Block.ParticleCollector.EnergyGeneration;

import com.ariks.torcherinoCe.Block.Core.ExampleContainer;
import com.ariks.torcherinoCe.Block.Core.SlotOut;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ContainerEnergyParticle extends ExampleContainer {
    public ContainerEnergyParticle(InventoryPlayer inventoryPlayer, TileEnergyParticle tileEntity) {
        super(tileEntity);
        for (int i = 0 ;i < 6; i++){
            this.addSlotToContainer(new SlotOut(tileEntity,i,60 + i * 18,12));
        }
        for (int i = 0 ;i < 6; i++){
            this.addSlotToContainer(new SlotOut(tileEntity,i+6,60 + i * 18,30));
        }
        for (int i = 0 ;i < 6; i++){
            this.addSlotToContainer(new SlotOut(tileEntity,i+12,60 + i * 18,48));
        }
        PlayerInventory(inventoryPlayer);
    }

    @Override
    public @NotNull ItemStack transferStackInSlot(@NotNull EntityPlayer player, int slotIndex) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(slotIndex);
        if (slot != null && slot.getHasStack()) {
            ItemStack slotStack = slot.getStack();
            itemstack = slotStack.copy();
            if (slotIndex < 18) {
                if (!this.mergeItemStack(slotStack, 18, 36 + 18, false)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (!this.mergeItemStack(slotStack, 0, 0, false)) {
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