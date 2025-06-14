package com.ariks.torcherinoCe.Block.Time;

import com.ariks.torcherinoCe.Block.Core.TileExampleContainer;
import net.minecraft.nbt.NBTTagCompound;
import org.jetbrains.annotations.NotNull;

public abstract class TileTime extends TileExampleContainer {
    public final EnergyTime energyTime;

    public TileTime(int MaxStorage) {
        energyTime = new EnergyTime(MaxStorage);
    }
    @Override
    public int getValue(int id) {
        if (id == 1) return this.energyTime.getTimeStored();
        return id;
    }
    @Override
    public void setValue(int id, int value) {
        if (id == 1) this.energyTime.setTime(value);
    }
    @Override
    public int[] getValueList() {
        return new int[]{1};
    }
    public int getMaxTimeStored(){
        return energyTime.getMaxTimeStored();
    }
    @Override
    public @NotNull NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt.setInteger("EnergyTime",energyTime.getTimeStored());
        return super.writeToNBT(nbt);
    }
    @Override
    public void readFromNBT(@NotNull NBTTagCompound nbt) {
        energyTime.setTime(nbt.getInteger("EnergyTime"));
        super.readFromNBT(nbt);
    }
}