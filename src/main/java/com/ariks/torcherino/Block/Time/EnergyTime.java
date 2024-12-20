package com.ariks.torcherino.Block.Time;

import com.ariks.torcherino.Block.Core.TileExampleContainer;

public class EnergyTime {
    protected int time;
    protected int capacity;
    private final TileExampleContainer tile;
    public EnergyTime(int capacity,TileExampleContainer tile)
    {
        this(capacity, 0,tile);
    }
    public EnergyTime(int capacity, int time, TileExampleContainer tile)
    {
        this.capacity = capacity;
        this.time = Math.max(0 , Math.min(capacity, time));
        this.tile = tile;
    }
    public void producedTime(int amount) {
        int energyAdded = Math.min(capacity - time, amount);
        time += energyAdded;
        EnergyChange();
    }
    public void consumeTime(int amount) {
        int energyConsumed = Math.min(time, amount);
        time -= energyConsumed;
        EnergyChange();
    }
    public void setTime(int time){
        this.time = time;
        EnergyChange();
    }
    public int getTimeStored()
    {
        return time;
    }
    public int getMaxTimeStored()
    {
        return capacity;
    }
    public void EnergyChange(){
        this.tile.markDirty();
    }
}