package com.ariks.torcherinoCe.Block.Time.TimeManipulator;

import com.ariks.torcherinoCe.Block.Time.TileTime;
import com.ariks.torcherinoCe.Register.RegistryGui;
import com.ariks.torcherinoCe.utility.Config;
import org.jetbrains.annotations.NotNull;

public class TileTimeManipulator extends TileTime {

    private final int RequiredTimeManipulator = Config.RequiredTimeManipulator;

    public int getRequiredTimeManipulator() {
        return RequiredTimeManipulator;
    }
    public TileTimeManipulator(){
        super(Config.MaxStorageTimeManipulator);
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
        this.energyTime.consumeTime(RequiredTimeManipulator);
    }
    @Override
    public @NotNull String getGuiID() {
        return String.valueOf(RegistryGui.GUI_TIME_MANIPULATOR);
    }
}