package com.artiks.torcherinoCe.Block.Core;

import com.artiks.torcherinoCe.utility.EnergyStorage;
import com.artiks.torcherinoCe.utility.IEnergyTile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public abstract class TileExampleInventoryEnergy extends TileExampleInventory implements IEnergyTile {

    private final EnergyStorage Storage;

    public TileExampleInventoryEnergy(int InventorySize,int capacity,int maxReceive,int maxExtract) {
        super(InventorySize);
        Storage = new EnergyStorage(capacity,maxReceive,maxExtract);
    }

    @Override
    public <T> T getCapability(@NotNull Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY) {
            return (T) Storage;
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

    @Override
    public int getEnergyStored() {
        return Storage.getEnergyStored();
    }

    @Override
    public void setUseEnergy(boolean useEnergy) {
        Storage.setUseEnergy(useEnergy);
    }

    @Override
    public void setEnergyStorage(int energy) {
        Storage.setEnergy(energy);
    }

    public void consumeEnergy(int consume){
        Storage.consumeEnergy(consume);
    }

    @Override
    public int getMaxEnergyStorage() {
        return Storage.getMaxEnergyStored();
    }

    @Override
    public @NotNull NBTTagCompound writeToNBT(@NotNull NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("energy", Storage.getEnergyStored());
        nbt.setBoolean("useEnergy",Storage.getUseEnergy());
        return nbt;
    }

    @Override
    public void getSyncData(Map<String, Object> data) {
        data.put("Energy",Storage.getEnergyStored());
    }

    @Override
    public void setSyncData(Map<String, Object> data) {
        if(data.containsKey("Energy")){
            Storage.setEnergy((int) data.get("Energy"));
        }
    }

    @Override
    public void readFromNBT(@NotNull NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        Storage.setEnergy(nbt.getInteger("energy"));
        Storage.setUseEnergy(nbt.getBoolean("useEnergy"));
    }
}
