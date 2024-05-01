package com.ariks.torcherino.Block.Aceleration;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerAceleration extends Container {
    private final TileAcceleration tileEntity;
    private int TimeStorage;
    public ContainerAceleration(InventoryPlayer inventoryPlayer, TileAcceleration tileEntity, EntityPlayer entityPlayer) {
        this.tileEntity = tileEntity;
    }
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        int newTimeStorage = this.tileEntity.getValue(1);
        if (this.TimeStorage != newTimeStorage) {
            this.TimeStorage = newTimeStorage;
            for (IContainerListener listener : this.listeners) {
                listener.sendWindowProperty(this, 1, newTimeStorage);
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