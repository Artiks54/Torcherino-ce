package com.artiks.torcherinoCe.Block.Energy.ParticleEnergyGeneration;

import com.artiks.torcherinoCe.Block.Core.TileExampleInventoryEnergy;
import com.artiks.torcherinoCe.Register.RegistryGui;
import com.artiks.torcherinoCe.Register.RegistryItems;
import com.artiks.torcherinoCe.utility.Config;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

public class TileEnergyParticle extends TileExampleInventoryEnergy{
    private int Amount = 1;
    private final int energyPerTick = Config.RFPerTickEnergyParticle;
    private final int MaxProgress = Config.MaxEnergyParticleProgress;
    private int Progress;
    public int getMaxProgress() {return MaxProgress;}
    public int getEnergyPerTick() {return energyPerTick;}
    public void updateAmount(int newAmount) {this.Amount = newAmount;}
    public int getProgress() {return Progress;}

    public TileEnergyParticle() {
        super(21, Config.MaxEnergyParticle, Integer.MAX_VALUE, 0);
        setSlotsForExtract(3, 20);
    }

    @Override
    public void update() {
        super.update();
        if (!world.isRemote) {
            this.generation();
        }
    }

    private void generation() {
        if (getEnergyStored() < energyPerTick) {
            return;
        }
        if (this.hasValidOutputSpace()) {
            Progress++;
            consumeEnergy(energyPerTick);
            if (Progress >= MaxProgress) {
                if (this.Generate()) {
                    Progress = 0;
                }
            }
        } else {
            Progress = 0;
        }
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
        return nbt;
    }

    @Override
    public void readFromNBT(@NotNull NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        Amount = nbt.getInteger("Amount");
    }

    @Override
    public void getSyncData(Map<String, Object> data) {
        super.getSyncData(data);
        data.put("Progress", Progress);
    }

    @Override
    public void setSyncData(Map<String, Object> data) {
        super.setSyncData(data);
        if (data.containsKey("Progress")) {
            this.Progress = (int) data.get("Progress");
        }
    }

    @Override
    public @NotNull String getGuiID() {
        return String.valueOf(RegistryGui.GUI_ENERGY_PARTICLE);
    }
}