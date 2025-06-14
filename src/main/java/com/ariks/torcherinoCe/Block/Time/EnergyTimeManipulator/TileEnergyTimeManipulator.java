package com.ariks.torcherinoCe.Block.Time.EnergyTimeManipulator;

import com.ariks.torcherinoCe.Block.Core.TileExampleContainer;
import com.ariks.torcherinoCe.Register.RegistryGui;
import com.ariks.torcherinoCe.utility.Config;
import com.ariks.torcherinoCe.utility.EnergyStorage;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import org.jetbrains.annotations.NotNull;

public class TileEnergyTimeManipulator extends TileExampleContainer {

    private final EnergyStorage storage;
    private final int needEnergy = Config.RequiredEnergyTimeManipulator;

    public TileEnergyTimeManipulator(){
        this.storage = new EnergyStorage(Config.MaxEnergyTimeManipulator,Integer.MAX_VALUE,0,this);
    }
    public void SetDay(){
        world.setWorldTime(1000);
        this.Reset();
    }
    public void SetNight() {
        world.setWorldTime(13000);
        this.Reset();
    }
    public void SetRain() {
        world.getWorldInfo().setRaining(true);
        world.getWorldInfo().setRainTime(12000);
        this.Reset();
    }
    public void ClearRain() {
        world.getWorldInfo().setRaining(false);
        world.getWorldInfo().setRainTime(0);
        this.Reset();
    }
    private void Reset(){
        storage.consumeEnergy(needEnergy);
    }
    @Override
    public @NotNull String getGuiID() {
        return String.valueOf(RegistryGui.GUI_ENERGY_TIME_MANIPULATOR);
    }
    @Override
    public int getValue(int id) {
        if(id == 1) return storage.getEnergyStored();
        return id;
    }
    @Override
    public void setValue(int id, int value) {
        if (id == 1) storage.setEnergy(value);
    }
    @Override
    public int[] getValueList() {
        return new int[]{1};
    }
    public int getNeedEnergy() {
        return needEnergy;
    }
    public int getMaxEnergyStorage() {
        return storage.getMaxEnergyStored();
    }
    public @NotNull NBTTagCompound writeToNBT(@NotNull NBTTagCompound nbt) {
        nbt.setInteger("Stored",this.storage.getEnergyStored());
        return super.writeToNBT(nbt);
    }
    @Override
    public void readFromNBT(@NotNull NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.storage.setEnergy(nbt.getInteger("Stored"));
    }
    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(@NotNull Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY) {
            return (T) storage;
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