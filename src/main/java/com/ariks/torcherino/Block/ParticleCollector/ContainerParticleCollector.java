package com.ariks.torcherino.Block.ParticleCollector;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

public class ContainerParticleCollector extends Container {
    private final TileParticleCollector tileEntity;
    private int progress;
    private int MaxProgress;

    public ContainerParticleCollector(InventoryPlayer playerInventory, TileParticleCollector tileEntity, EntityPlayer player) {
        this.tileEntity = tileEntity;
        this.addSlotToContainer(new Slot(tileEntity, 0, 80, 31) {
            @Override
            public boolean isItemValid(@NotNull ItemStack stack) {
                return false;
            }
        });
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 86 + i * 18));
            }
        }
        for (int k = 0; k < 9; ++k) {
            this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 144));
        }
    }
    @Override
    public @NotNull ItemStack transferStackInSlot(@NotNull EntityPlayer player, int slotIndex) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(slotIndex);
        if (slot != null && slot.getHasStack()) {
            ItemStack slotStack = slot.getStack();
            itemstack = slotStack.copy();
            if (slotIndex == 0) {
                if (!this.mergeItemStack(slotStack, 1, 37, false)) {
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(slotStack, itemstack);
            } else {
                if (slotIndex < 28) {
                    if (!this.mergeItemStack(slotStack, 28, 37, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (slotIndex < 37) {
                    if (!this.mergeItemStack(slotStack, 1, 28, false)) {
                        return ItemStack.EMPTY;
                    }
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
    @Override
    public void addListener(@NotNull IContainerListener listener) {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, this.tileEntity);
    }
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        int newProgress = this.tileEntity.getField(1);
        int newMaxProgress = this.tileEntity.getField(2);
        if (this.progress != newProgress) {
            for (IContainerListener listener : this.listeners) {
                listener.sendWindowProperty(this, 1, newProgress);
            }
            this.progress = newProgress;
        }
        if (this.MaxProgress != newMaxProgress) {
            for (IContainerListener listener : this.listeners) {
                listener.sendWindowProperty(this, 2, newMaxProgress);
            }
            this.MaxProgress = newMaxProgress;
        }
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data) {
        this.tileEntity.setField(id, data);
    }
    @Override
    public boolean canInteractWith(@NotNull EntityPlayer entityPlayer) {
        return this.tileEntity.isUsableByPlayer(entityPlayer);
    }
    @Override
    public void onContainerClosed(@NotNull EntityPlayer playerIn) {
        super.onContainerClosed(playerIn);
        this.tileEntity.closeInventory(playerIn);
    }
}