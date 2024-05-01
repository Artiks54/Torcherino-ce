package com.ariks.torcherino.Block.TimeStorage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerTimeStorage extends Container {
    private final TileTimeStorage tileEntity;
    private int TimeStorage,TimeStorageMax;
    public ContainerTimeStorage(InventoryPlayer inventoryPlayer, TileTimeStorage tileEntity,EntityPlayer entityPlayer) {
        this.tileEntity = tileEntity;
    }
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        int newTimeStorage = this.tileEntity.getValue(1);
        int newTimeStorageMax = this.tileEntity.getValue(2);
        if (this.TimeStorage != newTimeStorage || this.TimeStorageMax != newTimeStorageMax) {
            this.TimeStorage = newTimeStorage;
            this.TimeStorageMax = newTimeStorageMax;
            for (IContainerListener listener : this.listeners) {
                listener.sendWindowProperty(this, 1, newTimeStorage);
                listener.sendWindowProperty(this, 2, newTimeStorageMax);
            }
        }
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data) {
        this.tileEntity.setValue(id, data);
    }
    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return this.tileEntity.isUsableByPlayer(entityPlayer);
    }
}
