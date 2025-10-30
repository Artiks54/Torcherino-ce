package com.artiks.torcherinoCe.Block.Energy.EnergyTimeManipulator;

import com.artiks.torcherinoCe.Block.Core.TileExampleInventoryEnergy;
import com.artiks.torcherinoCe.Register.RegistryGui;
import com.artiks.torcherinoCe.utility.Config;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

public class TileEnergyTimeManipulator extends TileExampleInventoryEnergy {

    private final int needEnergy = Config.RequiredEnergyTimeManipulator;

    public TileEnergyTimeManipulator(){
        super(1,Config.MaxEnergyTimeManipulator,Integer.MAX_VALUE,0);
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
        consumeEnergy(needEnergy);
    }

    @Override
    public @NotNull String getGuiID() {
        return String.valueOf(RegistryGui.GUI_ENERGY_TIME_MANIPULATOR);
    }

    public int getNeedEnergy() {
        return needEnergy;
    }

    @Override
    public void getSyncData(Map<String, Object> data) {
        super.getSyncData(data);
        data.put("energy",getEnergyStored());
    }

    @Override
    public void setSyncData(Map<String, Object> data) {
        super.setSyncData(data);
        if(data.containsKey("energyTime")){
            setEnergyStorage((int)data.get("energyTime"));
        }
    }
}