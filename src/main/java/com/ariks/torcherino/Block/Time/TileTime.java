package com.ariks.torcherino.Block.Time;

import com.ariks.torcherino.Block.Core.TileExampleContainer;
import net.minecraft.nbt.NBTTagCompound;
import org.jetbrains.annotations.NotNull;

public class TileTime extends TileExampleContainer {
    public final EnergyTime energyTime;
    public TileTime(int MaxStorage) {
        energyTime = new EnergyTime(MaxStorage,this);
    }
    @Override
    public int getValue(int id) {
        if (id == 1) {
            return this.energyTime.getTimeStored();
        }
        if (id == 2) {
            return this.energyTime.getMaxTimeStored();
        }
        return id;
    }
    @Override
    public void setValue(int id, int value) {
    }
    @Override
    public @NotNull NBTTagCompound writeToNBT(@NotNull NBTTagCompound nbt) {
        nbt.setInteger("EnergyTime",energyTime.getTimeStored());
        return super.writeToNBT(nbt);
    }
    @Override
    public void readFromNBT(@NotNull NBTTagCompound nbt) {
        energyTime.setTime(nbt.getInteger("EnergyTime"));
        super.readFromNBT(nbt);
    }
    @Override
    public String getGuiID() {
        return "";
    }
}