package com.ariks.torcherino.Block.Time.TimeEnergyCollector;

import com.ariks.torcherino.Block.Time.TileTime;
import com.ariks.torcherino.Register.RegistryGui;
import com.ariks.torcherino.util.Config;
import com.ariks.torcherino.util.EnergyStorage;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import org.jetbrains.annotations.NotNull;

public class TileEnergyCollectors extends TileTime implements ITickable {
    private final EnergyStorage storage;
    public TileEnergyCollectors() {
        super(Config.ETC_TimeStorage);
        storage = new EnergyStorage(Config.ETC_EnergyStorage,Integer.MAX_VALUE,0,this);
    }
    @Override
    public void update() {
        if (!world.isRemote) {
            if (storage.getEnergyStored() >= Config.ETC_EnergyPerTick && energyTime.getTimeStored() <= energyTime.getMaxTimeStored()) {
                this.UpdateTile();
                if (storage.getEnergyStored() >= Config.ETC_EnergyPerTick && energyTime.getTimeStored() < energyTime.getMaxTimeStored()) {
                    storage.consumeEnergy(Config.ETC_EnergyPerTick);
                    energyTime.producedTime(Config.ETC_TimeGenerated);
                }
            }
        }
    }
    @Override
    public int getValue(int id) {
        if (id == 1) {
            return this.energyTime.getTimeStored();
        }
        if (id == 2) {
            return this.energyTime.getMaxTimeStored();
        }
        if (id == 3) {
            return this.storage.getEnergyStored();
        }
        if (id == 4) {
            return this.storage.getMaxEnergyStored();
        }
        return id;
    }
    @Override
    public @NotNull NBTTagCompound writeToNBT(@NotNull NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("EnergyRF",storage.getEnergyStored());
        nbt.setInteger("EnergyTime",energyTime.getTimeStored());
        return nbt;
    }
    @Override
    public void readFromNBT(@NotNull NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        storage.setEnergy(nbt.getInteger("EnergyRF"));
        energyTime.setTime(nbt.getInteger("EnergyTime"));
    }
    @Override
    public @NotNull String getGuiID() {
        return String.valueOf(RegistryGui.GUI_ENERGY_COLLECTORS_TIME);
    }
    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(@NotNull Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY)
        {
            return (T) storage;
        }
        return super.getCapability(capability, facing);
    }
    @Override
    public boolean hasCapability(@NotNull Capability<?> capability, EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY)
        {
            return true;
        }
        return super.hasCapability(capability, facing);
    }
}