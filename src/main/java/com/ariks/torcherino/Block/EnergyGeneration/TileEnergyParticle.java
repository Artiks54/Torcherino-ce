package com.ariks.torcherino.Block.EnergyGeneration;

import com.ariks.torcherino.Block.Core.TileExampleInventory;
import com.ariks.torcherino.Register.RegistryGui;
import com.ariks.torcherino.Register.RegistryItems;
import com.ariks.torcherino.util.Config;
import com.ariks.torcherino.util.EnergyStorage;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import org.jetbrains.annotations.NotNull;

public class TileEnergyParticle extends TileExampleInventory implements ITickable {
    private final EnergyStorage storage;
    private int MaxStorage = Config.MaxEnergyParticle;
    private int EnergyPerTick = Config.RFPerTickEnergyParticle;
    private int MaxProgres = Config.MaxEnergyParticleProgress;
    private int progress = 0;
    public TileEnergyParticle() {
        super(18);
        setSlotsForExtract(0,18);
        storage = new EnergyStorage(MaxStorage,Integer.MAX_VALUE,0);
    }
    @Override
    public void update() {
        if (!world.isRemote) {
            if(this.CanGenerate()) {
                this.Gen();
            }
        }
    }
    private boolean CanGenerate() {
            ItemStack item = new ItemStack(RegistryItems.time_particle);
            for (int i = 0; i < this.getSizeInventory(); i++) {
                ItemStack stack = this.getStackInSlot(i);
                if (stack.isEmpty() || (stack.getItem() == item.getItem() && ItemStack.areItemStackTagsEqual(stack, item) && stack.getCount() < stack.getMaxStackSize())) {
                    return true;
                }
            }
        return false;
    }
    private void Gen() {
        if (storage.getEnergyStored() >= EnergyPerTick) {
            storage.consumeEnergy(EnergyPerTick);
            progress++;
            this.UpdateTile();
            if (progress >= MaxProgres) {
                progress = 0;
                ItemStack item = new ItemStack(RegistryItems.time_particle);
                int amount = 1;
                for (int i = 0; i < this.getSizeInventory(); i++) {
                    ItemStack stack = this.getStackInSlot(i);
                    if (stack.isEmpty() || (stack.getItem() == item.getItem() && ItemStack.areItemStackTagsEqual(stack, item))) {
                        int amountToAdd = Math.min(amount, stack.isEmpty() ? item.getMaxStackSize() : stack.getMaxStackSize() - stack.getCount());
                        if (stack.isEmpty()) {
                            this.setInventorySlotContents(i, new ItemStack(item.getItem(), amountToAdd));
                        } else {
                            stack.grow(amountToAdd);
                        }
                        amount -= amountToAdd;
                    }
                }
            }
        }
    }
    @Override
    public @NotNull NBTTagCompound writeToNBT(@NotNull NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("Stored",storage.getEnergyStored());
        nbt.setInteger("Progress",progress);
        return nbt;
    }
    @Override
    public void readFromNBT(@NotNull NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        storage.setEnergy(nbt.getInteger("Stored"));
        progress = nbt.getInteger("Progress");
    }
    @Override
    public int getValue(int id) {
        if (id == 1) {
            return this.storage.getEnergyStored();
        }
        if (id == 2) {
            return this.MaxStorage;
        }
        if (id == 3) {
            return this.progress;
        }
        if (id == 4) {
            return this.MaxProgres;
        }
        return id;
    }
    @Override
    public String getGuiID() {
        return String.valueOf(RegistryGui.GUI_ENERGY_PARTICLE);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY)
        {
            return (T) storage;
        }
        return super.getCapability(capability, facing);
    }
    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY)
        {
            return true;
        }
        return super.hasCapability(capability, facing);
    }
}