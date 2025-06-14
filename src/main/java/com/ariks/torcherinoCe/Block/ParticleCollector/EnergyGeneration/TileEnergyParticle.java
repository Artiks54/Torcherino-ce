package com.ariks.torcherinoCe.Block.ParticleCollector.EnergyGeneration;

import com.ariks.torcherinoCe.Block.Core.TileExampleInventory;
import com.ariks.torcherinoCe.Register.RegistryGui;
import com.ariks.torcherinoCe.Register.RegistryItems;
import com.ariks.torcherinoCe.utility.Config;
import com.ariks.torcherinoCe.utility.EnergyStorage;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import org.jetbrains.annotations.NotNull;

public class TileEnergyParticle extends TileExampleInventory implements ITickable {
    private final EnergyStorage storage;
    private final int energyPerTick = Config.RFPerTickEnergyParticle;
    private final int maxProgress = Config.MaxEnergyParticleProgress;
    private int progress;

    public TileEnergyParticle() {
        super(18);
        setSlotsForExtract(0,18);
        int maxEnergyParticle = Config.MaxEnergyParticle;
        storage = new EnergyStorage(maxEnergyParticle,Integer.MAX_VALUE,0);
    }

    public int getMaxProgress() {
        return maxProgress;
    }
    public int getMaxEnergyStorage(){
        return storage.getMaxEnergyStored();
    }

    @Override
    public void update() {
        if (!world.isRemote) {
            this.generation();
        }
    }
    private void generation() {
        if (storage.getEnergyStored() < energyPerTick) {
            return;
        }
        storage.consumeEnergy(energyPerTick);
        progress++;
        if (progress < maxProgress) {
            return;
        }
        progress = 0;
        ItemStack timeParticleStack = new ItemStack(RegistryItems.time_particle);
        for (int i = 0; i < this.getSizeInventory(); i++) {
            ItemStack slotStack = this.getStackInSlot(i);
            if (slotStack.isEmpty()) {
                this.setInventorySlotContents(i, new ItemStack(RegistryItems.time_particle, 1));
                return;
            }
            if (slotStack.getItem() == timeParticleStack.getItem() &&
                    ItemStack.areItemStackTagsEqual(slotStack, timeParticleStack) &&
                    slotStack.getCount() < slotStack.getMaxStackSize()) {
                slotStack.grow(1);
                return;
            }
        }
    }
    @Override
    public @NotNull NBTTagCompound writeToNBT(@NotNull NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("energy",storage.getEnergyStored());
        nbt.setInteger("progress",progress);
        return nbt;
    }
    @Override
    public void readFromNBT(@NotNull NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        storage.setEnergy(nbt.getInteger("energy"));
        progress = nbt.getInteger("progress");
    }
    @Override
    public int getValue(int id) {
        if (id == 1) return progress;
        if (id == 2) return storage.getEnergyStored();
        return id;
    }
    @Override
    public void setValue(int id, int value) {
        if(id == 1) progress = value;
        if(id == 2) storage.setEnergy(value);
    }
    @Override
    public int[] getValueList() {
        return new int[]{1,2};
    }
    @Override
    public @NotNull String getGuiID() {
        return String.valueOf(RegistryGui.GUI_ENERGY_PARTICLE);
    }
    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(@NotNull Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY) {
            return (T) storage;
        }
        return super.getCapability(capability, facing);
    }
    @Override
    public boolean hasCapability(@NotNull Capability<?> capability, EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }
}