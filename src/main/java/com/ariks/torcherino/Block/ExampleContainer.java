package com.ariks.torcherino.Block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ExampleContainer extends Container {

    protected TileExampleContainer tile;
    private int[] tileMap;
    private void SetTile(TileExampleContainer tile) {
        this.tile = tile;
        this.tileMap = new int[tile.getValueList().length];
    }
    public ExampleContainer(TileExampleContainer tile) {
        this.SetTile(tile);
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
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return this.tile.isUsableByPlayer(entityPlayer);
    }
}