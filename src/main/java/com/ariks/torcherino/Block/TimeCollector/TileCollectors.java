package com.ariks.torcherino.Block.TimeCollector;

import com.ariks.torcherino.Block.TileExampleContainer;
import com.ariks.torcherino.Items.ITileTimeStorage;
import com.ariks.torcherino.Register.RegistryGui;
import com.ariks.torcherino.util.Config;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
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
            }
        }
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
    public Container createContainer(InventoryPlayer inventoryPlayer, EntityPlayer entityPlayer) {
        return new ContainerTimeCollectors(inventoryPlayer,this,entityPlayer);
    }
    @Override
    public String getGuiID() {return String.valueOf(RegistryGui.GUI_COLLECTORS_TIME);}
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
    @Override
    public void addTimeStorage(int time) {
        this.TimeStorage += time;
    }
    @Override
    public void removeTimeStorage(int time) {
        this.TimeStorage -= time;
    }
    @Override
    public int getTimeStorage() {
        return this.TimeStorage;
    }
    @Override
    public int getMaxStorage() {
        return this.ConfigCollectorsMaxStorage;
    }
}