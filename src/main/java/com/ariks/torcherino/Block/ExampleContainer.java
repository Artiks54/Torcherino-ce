package com.ariks.torcherino.Block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ExampleContainer extends Container {

    protected TileExampleContainer tile;
    private int[] tileMap;
    private void setTile(TileExampleContainer tile) {
        this.tile = tile;
        this.tileMap = new int[tile.getFieldOrdinals().length];
    }
    public ExampleContainer(TileExampleContainer tile) {
        this.setTile(tile);
    }
    protected void syncFields() {
        int fieldId;
        for (IContainerListener listener : this.listeners) {
            for (int j = 0; j < tile.getFieldOrdinals().length; j++) {
                fieldId = tile.getFieldOrdinals()[j];
                int value = this.tile.getValue(fieldId);
                if (this.tileMap[j] != value) {
                    listener.sendWindowProperty(this, fieldId, value);
                    this.tileMap[j] = value;
                }
            }
        }
    }
    @Override
    public void detectAndSendChanges() {
        if (tile != null) {
            this.syncFields();
        }
        super.detectAndSendChanges();
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data) {
        tile.setValue(id, data);
    }
    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return this.tile.isUsableByPlayer(entityPlayer);
    }
}