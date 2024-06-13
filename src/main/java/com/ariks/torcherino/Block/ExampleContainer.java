package com.ariks.torcherino.Block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

public class ExampleContainer extends Container {

    protected TileExampleContainer tile;
    private int[] tileMap;
    private void SetTile(TileExampleContainer tile) {
        this.tile = tile;
        this.tileMap = new int[tile.getValueList().length];
    }
    public ExampleContainer(TileExampleContainer tile, InventoryPlayer inventoryPlayer,boolean EnableInventory) {
        this.SetTile(tile);
        if(EnableInventory) {
            for (int i = 0; i < 3; ++i) {
                for (int j = 0; j < 9; ++j) {
                    this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 86 + i * 18));
                }
            }
            for (int k = 0; k < 9; ++k) {
                this.addSlotToContainer(new Slot(inventoryPlayer, k, 8 + k * 18, 144));
            }
        }
    }
    protected void SyncValue() {
        int ValueId;
        for (IContainerListener listener : this.listeners) {
            for (int j = 0; j < tile.getValueList().length; j++) {
                ValueId = tile.getValueList()[j];
                int value = this.tile.getValue(ValueId);
                if (this.tileMap[j] != value) {
                    listener.sendWindowProperty(this, ValueId, value);
                    this.tileMap[j] = value;
                }
            }
        }
    }
    @Override
    public void detectAndSendChanges() {
        if (tile != null) {
            this.SyncValue();
        }
        super.detectAndSendChanges();
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data) {
        tile.setValue(id, data);
    }
    @Override
    public boolean canInteractWith(@NotNull EntityPlayer entityPlayer) {
        return this.tile.isUsableByPlayer(entityPlayer);
    }
    @Override
    public void onContainerClosed(@NotNull EntityPlayer playerIn) {
        super.onContainerClosed(playerIn);
    }
    public @NotNull ItemStack transferStackInSlot(@NotNull EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index < this.inventorySlots.size() / 2) {
                if (!this.mergeItemStack(itemstack1, this.inventorySlots.size() / 2, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, this.inventorySlots.size() / 2, false)) {
                return ItemStack.EMPTY;
            }
            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }
        return itemstack;
    }
}