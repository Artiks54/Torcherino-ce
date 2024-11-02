package com.ariks.torcherino.Block.Time;

import com.ariks.torcherino.Block.Core.TileExampleContainer;
import com.ariks.torcherino.util.ITileTimeStorage;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import org.jetbrains.annotations.NotNull;

public class TileTime extends TileExampleContainer implements ITickable, ITileTimeStorage {

    private int TimeStorage;
    public int TimeStorageMax = 10;

    public TileTime(int TimeStorageMax) {
        this.TimeStorageMax = TimeStorageMax;
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
        return this.TimeStorageMax;
    }
    @Override
    public void update() {
    }
    public void setTimeStorage(int timeStorage) {
        TimeStorage = timeStorage;
        this.UpdateTile();
    }
    public void setTimeStorageMax(int timeStorageMax) {
        TimeStorageMax = timeStorageMax;
        this.UpdateTile();
    }
    @Override
    public @NotNull NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt.setInteger("TimeStorage", TimeStorage);
        return super.writeToNBT(nbt);
    }
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        this.TimeStorage = nbt.getInteger("TimeStorage");
        super.readFromNBT(nbt);
    }
    @Override
    public int getValue(int id) {
        if (id == 1) {
            return this.GetTimeStorage();
        }
        if (id == 2) {
            return this.GetMaxStorage();
        }
        return id;
    }
    @Override
    public void setValue(int id, int value) {
        if (id == 1) {
            this.GetTimeStorage();
        }
        if (id == 2) {
            this.GetMaxStorage();
        }
    }

    @Override
    public String getGuiID() {
        return "";
    }
}
