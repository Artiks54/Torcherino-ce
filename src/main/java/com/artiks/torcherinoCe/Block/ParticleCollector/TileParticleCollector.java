package com.artiks.torcherinoCe.Block.ParticleCollector;

import com.artiks.torcherinoCe.Block.Core.TileExampleInventory;
import com.artiks.torcherinoCe.Register.RegistryGui;
import com.artiks.torcherinoCe.Register.RegistryItems;
import com.artiks.torcherinoCe.utility.Config;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

public class TileParticleCollector extends TileExampleInventory implements ITickable {
    private int Amount = 1;
    private int AddProgress = 1;
    private int Progress;
    private final int MaxProgress = Config.RequiredGeneratorParticle;
    public int percent;
    public int getMaxProgress() {return MaxProgress;}
    public int getProgress(){return Progress;}

    public TileParticleCollector(){
        super(6);
        this.setSlotsForExtract(3,5);
    }

    @Override
    public void update() {
        super.update();
        if (!world.isRemote) {
            this.Work();
        }
    }

    private void Work() {
        if (this.hasValidOutputSpace()) {
            Progress += AddProgress;
            percent = (Progress * 100) / MaxProgress;
            if (Progress >= MaxProgress) {
                if (this.Generate()) {
                    Progress = 0;
                }
            }
        } else {
            Progress = 0;
        }
    }

    public void updateSpeed(int newSpeed) {
        this.AddProgress = newSpeed;
    }

    public void updateAmount(int newAmount) {
        this.Amount = newAmount;
    }

    private boolean Generate() {
        ItemStack item = new ItemStack(RegistryItems.time_particle);
        int amountToGenerate = Amount;
        for (int slot: invHandler.getSlotsExtract()) {
            ItemStack stack = this.getStackInSlot(slot);
            if (stack.isEmpty()) {
                int amountToAdd = Math.min(amountToGenerate, item.getMaxStackSize());
                this.setInventorySlotContents(slot, new ItemStack(item.getItem(), amountToAdd));
                amountToGenerate -= amountToAdd;
                if (amountToGenerate == 0) {
                    return true;
                }
            } else if (stack.getItem() == item.getItem() && ItemStack.areItemStackTagsEqual(stack, item)) {
                if (stack.getCount() < stack.getMaxStackSize()) {
                    int spaceLeft = stack.getMaxStackSize() - stack.getCount();
                    int amountToAdd = Math.min(amountToGenerate, spaceLeft);
                    stack.grow(amountToAdd);
                    amountToGenerate -= amountToAdd;
                    if (amountToGenerate == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public @NotNull NBTTagCompound writeToNBT(@NotNull NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("Amount",Amount);
        nbt.setInteger("AddProgress",AddProgress);
        nbt.setInteger("Progress",Progress);
        nbt.setTag("inventory", ItemStackHelper.saveAllItems(new NBTTagCompound(), inventory));
        return nbt;
    }

    @Override
    public void readFromNBT(@NotNull NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        Amount = nbt.getInteger("Amount");
        AddProgress = nbt.getInteger("AddProgress");
        Progress = nbt.getInteger("Progress");
        NBTTagCompound inventoryTag = nbt.getCompoundTag("inventory");
        ItemStackHelper.loadAllItems(inventoryTag, inventory);
    }

    @Override
    public void getSyncData(Map<String, Object> data) {
        data.put("Progress", Progress);
    }

    @Override
    public void setSyncData(Map<String, Object> data) {
        if (data.containsKey("Progress")) {
            this.Progress = (int) data.get("Progress");
        }
    }



    @Override
    public @NotNull String getGuiID() {
        return String.valueOf(RegistryGui.GUI_PARTICLE_COLLECTOR);
    }
}