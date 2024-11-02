package com.ariks.torcherino.Block.Time.TimeCollector;

import com.ariks.torcherino.Block.Time.TileTime;
import com.ariks.torcherino.Register.RegistryGui;
import com.ariks.torcherino.util.Config;
import net.minecraft.nbt.NBTTagCompound;
import org.jetbrains.annotations.NotNull;

public class TileCollectors extends TileTime {
    private int Cooldown;

    public TileCollectors(){
        super(Config.MaxStorageTimeCollector);
    }
    @Override
    public void update() {
        if(world.isRemote)return;
        this.timeCollects();
    }
    private void timeCollects(){
        if(++Cooldown >= 20){
            Cooldown = 0;
            if(GetTimeStorage() < GetMaxStorage()){
                AddTimeStorage(1);
                this.UpdateTile();
            }
        }
    }
    @Override
    public @NotNull String getGuiID() {
        return String.valueOf(RegistryGui.GUI_COLLECTORS_TIME);
    }
    @Override
    public @NotNull NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt.setInteger("Cooldown", Cooldown);
        return super.writeToNBT(nbt);
    }
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        this.Cooldown = nbt.getInteger("Cooldown");
        super.readFromNBT(nbt);
    }
}