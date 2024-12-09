package com.ariks.torcherino.util;

import com.ariks.torcherino.Block.Core.TileExampleContainer;
import net.minecraftforge.energy.IEnergyStorage;

public class EnergyStorage implements IEnergyStorage {
    private int energy;
    private final int capacity;
    private final int maxReceive;
    private final int maxExtract;
    private final TileExampleContainer tile;
    public EnergyStorage(int capacity, int maxReceive, int maxExtract, TileExampleContainer tile) {
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
        this.tile = tile;
    }
    public void setEnergy(int energy) {
        this.energy = Math.max(0, Math.min(energy, capacity));
        energyChange();
    }
    public void consumeEnergy(int amount) {
        int energyConsumed = Math.min(energy, amount);
        energy -= energyConsumed;
        energyChange();
    }
    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        int energyReceived = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive));
        if (!simulate) {
            energy += energyReceived;
            energyChange();
        }
        return energyReceived;
    }
    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));
        if (!simulate) {
            energy -= energyExtracted;
            energyChange();
        }
        return energyExtracted;
    }
    public void energyChange(){
        tile.UpdateTile();
    }
    @Override
    public int getEnergyStored() {
        return energy;
    }
    @Override
    public int getMaxEnergyStored() {
        return capacity;
    }
    @Override
    public boolean canExtract() {
        return this.maxExtract > 0;
    }
    @Override
    public boolean canReceive() {
        return this.maxReceive > 0;
    }
}