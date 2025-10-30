package com.artiks.torcherinoCe.utility;

public interface IEnergyTile {
    void setUseEnergy(boolean useEnergy);
    void setEnergyStorage(int energy);
    int getMaxEnergyStorage();
    int getEnergyStored();
}