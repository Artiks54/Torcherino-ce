package com.artiks.torcherinoCe.Block.Energy.TimeEnergyCollector;

import com.artiks.torcherinoCe.Block.Time.TileTime;
import com.artiks.torcherinoCe.Register.RegistryGui;
import com.artiks.torcherinoCe.utility.Config;
import com.artiks.torcherinoCe.utility.EnergyStorage;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

public class TileEnergyCollectors extends TileTime implements ITickable {
    private final EnergyStorage EnergyStorage;
    public TileEnergyCollectors() {
        super(Config.ETC_TimeStorage);
        EnergyStorage = new EnergyStorage(Config.ETC_EnergyStorage, Integer.MAX_VALUE,0);
    }
    @Override
    public void update() {
        if (!world.isRemote) {
            if (EnergyStorage.getEnergyStored() >= Config.ETC_EnergyPerTick && energyTime.getTimeStored() < energyTime.getMaxTimeStored()) {
                EnergyStorage.consumeEnergy(Config.ETC_EnergyPerTick);
                energyTime.producedTime(Config.ETC_TimeGenerated);
            }
        }
    }
    public int getEnergyPerTick() {
        return Config.ETC_EnergyPerTick;
    }

    @Override
    public void getSyncData(Map<String, Object> data) {
        super.getSyncData(data);
        data.put("energyTime",energyTime.getTimeStored());
        data.put("Energy",EnergyStorage.getEnergyStored());
    }

    @Override
    public void setSyncData(Map<String, Object> data) {
        super.setSyncData(data);
        if(data.containsKey("energyTime")){
            energyTime.setTime((int)data.get("energyTime"));
        }
        if(data.containsKey("Energy")){
            EnergyStorage.setEnergy((int) data.get("Energy"));
        }
    }

    public int getEnergyStorage() {
        return EnergyStorage.getEnergyStored();
    }

    public int getMaxEnergyStorage() {
        return EnergyStorage.getMaxEnergyStored();
    }

    @Override
    public @NotNull NBTTagCompound writeToNBT(@NotNull NBTTagCompound nbt) {
        nbt.setInteger("EnergyRF",EnergyStorage.getEnergyStored());
        return super.writeToNBT(nbt);
    }

    @Override
    public void readFromNBT(@NotNull NBTTagCompound nbt) {
        EnergyStorage.setEnergy(nbt.getInteger("EnergyRF"));
        super.readFromNBT(nbt);
    }

    @Override
    public @NotNull String getGuiID() {
        return String.valueOf(RegistryGui.GUI_ENERGY_COLLECTORS_TIME);
    }

    @Override
    public <T> T getCapability(@NotNull Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY) {
            return (T) EnergyStorage;
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability(@NotNull Capability<?> capability, EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }
}