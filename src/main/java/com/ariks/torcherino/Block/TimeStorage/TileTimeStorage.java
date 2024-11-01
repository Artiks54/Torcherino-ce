package com.ariks.torcherino.Block.TimeStorage;

import com.ariks.torcherino.util.ITileTimeStorage;
import com.ariks.torcherino.Register.RegistryGui;
import com.ariks.torcherino.Block.TileExampleContainer;
import com.ariks.torcherino.util.Config;
import net.minecraft.nbt.NBTTagCompound;
import org.jetbrains.annotations.NotNull;

public class TileTimeStorage extends TileExampleContainer implements ITileTimeStorage {
    private int TimeStorage;
    private int TimeStorageMax = Config.TimeStorageMaxTime;
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
    public int getValue(int id) {
        if (id == 1) {
            return this.TimeStorage;
        }
        if (id == 2) {
            return this.TimeStorageMax;
        }
        return id;
    }
    @Override
    public void setValue(int id, int value)
    {
        if (id == 1) {
            this.TimeStorage = value;
        }
        if (id == 2) {
            this.TimeStorageMax = value;
        }
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
    public @NotNull String getGuiID() {
        return String.valueOf(RegistryGui.GUI_TIME_STORAGE);
    }
}