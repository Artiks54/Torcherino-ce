package com.artiks.torcherinoCe.Block.Time;

public class EnergyTime {
    private int time;
    private final int capacity;

    public EnergyTime(int capacity)
    {
        this.capacity = capacity;
    }
    public void producedTime(int amount) {
        int energyAdded = Math.min(capacity - time, amount);
        time += energyAdded;
    }
    public void consumeTime(int amount) {
        int energyConsumed = Math.min(time, amount);
        time -= energyConsumed;
    }
    public void setTime(int time){
        this.time = Math.max(0 , Math.min(time, capacity));
    }
    public int getTimeStored()
    {
        return time;
    }
    public int getMaxTimeStored()
    {
        return capacity;
    }
}