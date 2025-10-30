package com.artiks.torcherinoCe.Block.Energy.ParticleEnergyGeneration;

import com.artiks.torcherinoCe.Block.Core.ExampleContainer;
import com.artiks.torcherinoCe.Block.Core.Slots.SlotEnergyModule;
import com.artiks.torcherinoCe.Block.Core.Slots.SlotItemExport;
import com.artiks.torcherinoCe.Block.Core.Slots.SlotOut;
import com.artiks.torcherinoCe.Block.Core.Slots.SlotUpgradeCount;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ContainerEnergyParticle extends ExampleContainer {
    final int totalSlots = 21;
    public ContainerEnergyParticle(InventoryPlayer inventoryPlayer, TileEnergyParticle tileEntity) {
        super(tileEntity);
        this.addSlotToContainer(new SlotItemExport(tileEntity, 0, 180, 5));
        this.addSlotToContainer(new SlotUpgradeCount(tileEntity, 1, 180, 30));
        this.addSlotToContainer(new SlotEnergyModule(tileEntity, 2, 180, 55));
        for (int i = 0; i < 6; i++) {
            this.addSlotToContainer(new SlotOut(tileEntity, i + 3, 60 + i * 18, 12));
        }
        for (int i = 0; i < 6; i++) {
            this.addSlotToContainer(new SlotOut(tileEntity, i + 9, 60 + i * 18, 30));  // исправлено с i+10 на i+9
        }
        for (int i = 0; i < 6; i++) {
            this.addSlotToContainer(new SlotOut(tileEntity, i + 15, 60 + i * 18, 48)); // исправлено с i+16 на i+15
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