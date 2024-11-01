package com.ariks.torcherino.Block.TimeManipulator;

import com.ariks.torcherino.Block.TileExampleContainer;
import com.ariks.torcherino.Register.RegistryGui;
import com.ariks.torcherino.util.Config;
import com.ariks.torcherino.util.ITileTimeStorage;
import net.minecraft.nbt.NBTTagCompound;
import org.jetbrains.annotations.NotNull;

public class TileTimeManipulator extends TileExampleContainer implements ITileTimeStorage {
    private int TimeStorage;
    private final int TimeMaxStorage = Config.RequiredTimeManipulator;
    public void SetDay(){
        world.setWorldTime(1000);
        this.Reset();
    }
    public void SetNight() {
        world.setWorldTime(13000);
        this.Reset();
    }
    private void Reset(){
        TimeStorage = 0;
        UpdateTile();
    }
    @Override
    public int getValue(int id) {
        if (id == 1) {
            return this.TimeStorage;
        }
        if (id == 2) {
            return this.TimeMaxStorage;
        }
        return id;
    }
    @Override
    public void setValue(int id, int value)
    {
        if (id == 1) {
            this.TimeStorage = value;
        }
    }
    @Override
    public @NotNull NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt.setInteger("Time", TimeStorage);
        return super.writeToNBT(nbt);
    }
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        this.TimeStorage = nbt.getInteger("Time");
        super.readFromNBT(nbt);
    }
    @Override
    public @NotNull String getGuiID() {return String.valueOf(RegistryGui.GUI_TIME_MANIPULATOR);
    }
    @Override
    public void AddTimeStorage(int time) {
        this.TimeStorage += time;
        this.UpdateTile();
    }
    @Override
    public void RemoveTimeStorage(int time) {
        this.TimeStorage -= time;
        this.UpdateTile();
    }
    @Override
    public int GetTimeStorage() {
        return this.TimeStorage;
    }
    @Override
    public int GetMaxStorage() {
        return this.TimeMaxStorage;
    }
}