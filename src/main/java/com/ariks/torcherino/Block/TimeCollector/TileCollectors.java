package com.ariks.torcherino.Block.TimeCollector;

import com.ariks.torcherino.Block.TileExampleContainer;
import com.ariks.torcherino.util.ITileTimeStorage;
import com.ariks.torcherino.Register.RegistryGui;
import com.ariks.torcherino.util.Config;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import org.jetbrains.annotations.NotNull;

public class TileCollectors extends TileExampleContainer implements ITickable, ITileTimeStorage {
    private int Cooldown,TimeStorage;
    private int ConfigCollectorsMaxStorage = Config.MaxStorageTimeCollectors;
    @Override
    public void update() {
        if(world.isRemote)return;
        this.timeCollects();
    }
    private void timeCollects(){
        if(++Cooldown >= 20){
            Cooldown = 0;
            if(TimeStorage < ConfigCollectorsMaxStorage){
                TimeStorage++;
                UpdateTile();
            }
        }
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
        return this.ConfigCollectorsMaxStorage;
    }
    @Override
    public int getValue(int id) {
        if (id == 1) {
            return this.TimeStorage;
        }
        if (id == 2) {
            return this.ConfigCollectorsMaxStorage;
        }
        return id;
    }
    @Override
    public void setValue(int id, int value) {
        if (id == 1) {
            this.TimeStorage = value;
        }
        if (id == 2) {
            this.ConfigCollectorsMaxStorage = value;
        }
    }
    @Override
    public @NotNull String getGuiID() {
        return String.valueOf(RegistryGui.GUI_COLLECTORS_TIME);
    }
    @Override
    public @NotNull NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt.setInteger("TimeStorage", TimeStorage);
        nbt.setInteger("Cooldown", Cooldown);
        return super.writeToNBT(nbt);
    }
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        this.TimeStorage = nbt.getInteger("TimeStorage");
        this.Cooldown = nbt.getInteger("Cooldown");
        super.readFromNBT(nbt);
    }
}