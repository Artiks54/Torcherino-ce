package com.ariks.torcherinoCe.Block.Time.TimeEnergyCollector;

import com.ariks.torcherinoCe.Block.Time.TileTime;
import com.ariks.torcherinoCe.Register.RegistryGui;
import com.ariks.torcherinoCe.utility.Config;
import com.ariks.torcherinoCe.utility.EnergyStorage;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import org.jetbrains.annotations.NotNull;

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
    public int getValue(int id) {
        if (id == 2) return EnergyStorage.getEnergyStored();
        return super.getValue(id);
    }
    @Override
    public void setValue(int id, int value) {
        if(id == 2) EnergyStorage.setEnergy(value);
        super.setValue(id, value);
    }
    @Override
    public int[] getValueList() {
        return new int[]{1,2};
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
    @SuppressWarnings("unchecked")
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