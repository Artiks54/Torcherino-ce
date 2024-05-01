package com.ariks.torcherino.Block.Torcherino;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerTorcherino extends Container {
    private final TileTorcherinoBase tileEntity;
    private int Working,CurrentRadius,CurrentSpeed,Render,MaxAcceleration;
    public ContainerTorcherino(InventoryPlayer inventoryPlayer, TileTorcherinoBase tileEntity, EntityPlayer entityPlayer) {
        this.tileEntity = tileEntity;
    }
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        int newWorking = this.tileEntity.getValue(1);
        int newCurrentRadius = this.tileEntity.getValue(2);
        int newCurrentSpeed = this.tileEntity.getValue(3);
        int newRender = this.tileEntity.getValue(4);
        int newMaxAcceleration = this.tileEntity.getValue(5);
        if (this.Working != newWorking || this.CurrentRadius != newCurrentRadius || this.CurrentSpeed != newCurrentSpeed || this.Render != newRender || this.MaxAcceleration != newMaxAcceleration) {
            this.Working = newWorking;
            this.CurrentRadius = newCurrentRadius;
            this.CurrentSpeed = newCurrentSpeed;
            this.Render = newRender;
            this.MaxAcceleration = newMaxAcceleration;
            for (IContainerListener listener : this.listeners) {
                listener.sendWindowProperty(this, 1, newWorking);
                listener.sendWindowProperty(this, 2, newCurrentRadius);
                listener.sendWindowProperty(this, 3, newCurrentSpeed);
                listener.sendWindowProperty(this, 4, newRender);
                listener.sendWindowProperty(this, 5, newMaxAcceleration);
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