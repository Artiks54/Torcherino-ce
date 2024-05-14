package com.ariks.torcherino.Block.Torcherino;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerTorcherino extends Container {
    private final TileTorcherinoBase tileEntity;
    private int Radius,Speed,work,render,R,G,B;
    public ContainerTorcherino(InventoryPlayer inventoryPlayer, TileTorcherinoBase tileEntity, EntityPlayer entityPlayer) {
        this.tileEntity = tileEntity;
    }
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        int newRadius = this.tileEntity.getValue(1);
        int newSpeed = this.tileEntity.getValue(2);
        int newWork = this.tileEntity.getValue(3);
        int newRender = this.tileEntity.getValue(4);
        int newR = this.tileEntity.getValue(8);
        int newG = this.tileEntity.getValue(9);
        int newB = this.tileEntity.getValue(10);
        if (this.Radius != newRadius
                || this.Speed != newSpeed
                || this.work != newWork
                || this.render != newRender
                || this.R != newR
                || this.G != newG
                || this.B != newB) {
            this.Radius = newRadius;
            this.Speed = newSpeed;
            this.work = newWork;
            this.render = newRender;
            this.R = newR;
            this.G = newG;
            this.B = newB;
            for (IContainerListener listener : this.listeners) {
                listener.sendWindowProperty(this, 1, newRadius);
                listener.sendWindowProperty(this, 2, newSpeed);
                listener.sendWindowProperty(this, 3, newWork);
                listener.sendWindowProperty(this, 4, newRender);
                listener.sendWindowProperty(this, 8, newR);
                listener.sendWindowProperty(this, 9, newG);
                listener.sendWindowProperty(this, 10, newB);
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