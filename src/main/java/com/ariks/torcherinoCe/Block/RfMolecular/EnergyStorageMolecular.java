package com.ariks.torcherinoCe.Block.RfMolecular;

import net.minecraftforge.energy.IEnergyStorage;

public class EnergyStorageMolecular implements IEnergyStorage {
    private int energy;
    private final int maxReceive;
    private int maxCapacity;

    public EnergyStorageMolecular(int maxReceive, int maxExtract, int maxCapacity) {
        this.maxReceive = maxReceive;
        this.maxCapacity = maxCapacity;
    }
    public void setEnergy(int energy){
        this.energy = energy;
    }
    public int consumeAllEnergy() {
        int consumed = energy;
        energy = 0;
        return consumed;
    }
    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        int energyReceived = Math.min(maxCapacity - energy, Math.min(this.maxReceive, maxReceive));
        if (!simulate) {
            energy += energyReceived;
        }
        return energyReceived;
    }
    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return 0;
    }
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
    @Override
    public int getEnergyStored() {
        return energy;
    }
    @Override
    public int getMaxEnergyStored() {
        return maxCapacity;
    }
    @Override
    public boolean canExtract() {
        return false;
    }
    @Override
    public boolean canReceive() {
        return true;
    }
}