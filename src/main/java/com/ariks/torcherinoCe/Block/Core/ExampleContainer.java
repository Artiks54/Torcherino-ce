package com.ariks.torcherinoCe.Block.Core;

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

    private final TileExampleContainer tileEntity;
    private final int[] tileMap;

    public ExampleContainer(TileExampleContainer tile) {
        this.tileEntity = tile;
        this.tileMap = new int[tile.getValueList().length];
    }
    protected void PlayerInventory(InventoryPlayer inventoryPlayer) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 86 + i * 18));
            }
        }
        PlayerHotbar(inventoryPlayer);
    }
    private void PlayerHotbar(InventoryPlayer inventoryPlayer) {
        for (int k = 0; k < 9; ++k) {
            this.addSlotToContainer(new Slot(inventoryPlayer, k, 8 + k * 18, 144));
        }
    }
    protected void SyncValue() {
        int ValueId;
        for (IContainerListener listener : this.listeners) {
            for (int j = 0; j < tileEntity.getValueList().length; j++) {
                ValueId = tileEntity.getValueList()[j];
                int value = this.tileEntity.getValue(ValueId);
                if (this.tileMap[j] != value) {
                    listener.sendWindowProperty(this, ValueId, value);
                    this.tileMap[j] = value;
                }
            }
        }
    }
    @Override
    public void detectAndSendChanges() {
        if (tileEntity != null) {
            this.SyncValue();
        }
        super.detectAndSendChanges();
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data) {
        tileEntity.setValue(id, data);
    }
    @Override
    public boolean canInteractWith(@NotNull EntityPlayer entityPlayer) {
        return this.tileEntity.isUsableByPlayer(entityPlayer);
    }
    @Override
    public @NotNull ItemStack transferStackInSlot(@NotNull EntityPlayer playerIn, int index) {
        return ItemStack.EMPTY;
    }
}