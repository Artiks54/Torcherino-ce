package com.artiks.torcherinoCe.Block.Time;

import com.artiks.torcherinoCe.Block.Core.TileExampleContainer;
import net.minecraft.nbt.NBTTagCompound;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

public abstract class TileTime extends TileExampleContainer {
    public final EnergyTime energyTime;

    public TileTime(int MaxStorage) {
        energyTime = new EnergyTime(MaxStorage);
    }

    @Override
    public void getSyncData(Map<String, Object> data) {
        data.put("energyTime",energyTime.getTimeStored());
    }

    @Override
    public void setSyncData(Map<String, Object> data) {
        if(data.containsKey("energyTime")){
            energyTime.setTime((int)data.get("energyTime"));
        }
    }

    public int getEnergyTime() {
        return energyTime.getTimeStored();
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