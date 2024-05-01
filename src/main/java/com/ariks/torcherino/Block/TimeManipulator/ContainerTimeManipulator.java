package com.ariks.torcherino.Block.TimeManipulator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerTimeManipulator extends Container {
    private final TileTimeManipulator tileEntity;
    private int TimeStorage;
    public ContainerTimeManipulator(InventoryPlayer inventoryPlayer, TileTimeManipulator tileEntity, EntityPlayer entityPlayer) {
        this.tileEntity = tileEntity;
    }
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        int value1 = this.tileEntity.getValue(1);
        if (this.TimeStorage != value1) {
            this.TimeStorage = value1;
            for (IContainerListener listener : this.listeners) {
                listener.sendWindowProperty(this, 1, value1);
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