package com.ariks.torcherino.Block.TimeManipulator;

import com.ariks.torcherino.Block.TileExampleContainer;
import com.ariks.torcherino.Register.RegistryGui;
import com.ariks.torcherino.util.Config;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import org.jetbrains.annotations.NotNull;

public class TileTimeManipulator extends TileExampleContainer implements ITickable {
    private int Charged,Cooldown,percent;
    private final int MaxCooldown = Config.RequiredTimeManipulator;
    @Override
    public void update() {
        if(world.isRemote)return;
        this.Charging();
    }
    private void Charging(){
        if (Charged == 0) {
            ++Cooldown;
            percent = (Cooldown *100)/MaxCooldown;
            if(Cooldown >= MaxCooldown){
                Charged = 1;
            }
            UpdateTile();
        }
    }
    public void SetDay(){
        world.setWorldTime(1000);
        this.Reset();
    }
    public void SetNight() {
        world.setWorldTime(13000);
        this.Reset();
    }
    private void Reset(){
        Charged = 0;
        Cooldown = 0;
        percent = 0;
        UpdateTile();
    }
    @Override
    public int getValue(int id) {
        if (id == 1) {
            return this.percent;
        }
        return id;
    }
    @Override
    public void setValue(int id, int value)
    {
        if (id == 1) {
            this.percent = value;
        }
    }
    @Override
    public @NotNull NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt.setInteger("Cooldown", Cooldown);
        nbt.setInteger("Charged",Charged);
        nbt.setInteger("Percent",percent);
        return super.writeToNBT(nbt);
    }
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        this.Cooldown = nbt.getInteger("Cooldown");
        this.Charged = nbt.getInteger("Charged");
        this.percent = nbt.getInteger("Percent");
        super.readFromNBT(nbt);
    }
    @Override
    public Container createContainer(InventoryPlayer inventoryPlayer, EntityPlayer entityPlayer) {
        return null;
    }
    @Override
    public String getGuiID() {return String.valueOf(RegistryGui.GUI_TIME_MANIPULATOR);}
}