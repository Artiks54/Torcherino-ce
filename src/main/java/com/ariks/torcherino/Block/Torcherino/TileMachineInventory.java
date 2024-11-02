package com.ariks.torcherino.Block.Torcherino;

import com.ariks.torcherino.Block.Core.TileExampleInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import org.jetbrains.annotations.NotNull;

public class TileMachineInventory extends TileExampleInventory implements ITickable {

    private int progress = 0;
    private int AddProgress = 1;
    private int MaxProgress;
    private int percent;

    public TileMachineInventory(int InventorySize) {
        super(InventorySize);
    }

    @Override
    public void update() {
        if(!world.isRemote){
            progress += AddProgress;
            this.UpdateTile();
            if(progress >= MaxProgress){
                progress = 0;
                this.Tick();
            }
            percent = (getProgress() * 100) / getMaxProgress();
        }
    }
    public int getPercent() {
        return percent;
    }
    public void clearProgress(){
        progress = 0;
    }
    public void Tick(){
    }
    public void setMaxProgress(int maxProgress) {
        MaxProgress = maxProgress;
    }
    public void setAddProgress(int addProgress){
        AddProgress = addProgress;
    }
    public int getProgress() {
        return progress;
    }
    public int getMaxProgress() {
        return MaxProgress;
    }
    @Override
    public @NotNull NBTTagCompound writeToNBT(@NotNull NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("progress", progress);
        nbt.setInteger("addProgress",AddProgress);
        nbt.setTag("inventory", ItemStackHelper.saveAllItems(new NBTTagCompound(), inventory));
        return nbt;
    }
    @Override
    public void readFromNBT(@NotNull NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        progress = nbt.getInteger("progress");
        AddProgress = nbt.getInteger("addProgress");
        NBTTagCompound inventoryTag = nbt.getCompoundTag("inventory");
        ItemStackHelper.loadAllItems(inventoryTag, inventory);
    }
}
