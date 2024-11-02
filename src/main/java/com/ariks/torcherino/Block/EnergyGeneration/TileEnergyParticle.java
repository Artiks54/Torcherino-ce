package com.ariks.torcherino.Block.EnergyGeneration;

import com.ariks.torcherino.Block.Core.TileExampleInventory;
import com.ariks.torcherino.Register.RegistryGui;
import com.ariks.torcherino.Register.RegistryItems;
import com.ariks.torcherino.util.Config;
import com.ariks.torcherino.util.EnergyStorage;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.energy.CapabilityEnergy;
import org.jetbrains.annotations.NotNull;


public class TileEnergyParticle extends TileExampleInventory implements ITickable {
    private final EnergyStorage storage;
    private int MaxStorage = Config.MaxEnergyParticle;
    private int EnergyPerTick = Config.RFPerTickEnergyParticle;
    public TileEnergyParticle() {
        super(18);
        setSlotsForExtract(0,18);
        storage = new EnergyStorage(MaxStorage,Integer.MAX_VALUE,0);
    }
    @Override
    public void update() {
        if (!world.isRemote) {
            if(this.CanGenerate() && storage.getEnergyStored() > 0 ) {
                this.UpdateTile();
                this.Generate();
            }
        }
    }
    private boolean CanGenerate() {
        if(storage.getEnergyStored() >= EnergyPerTick) {
            ItemStack item = new ItemStack(RegistryItems.time_particle);
            for (int i = 0; i < this.getSizeInventory(); i++) {
                ItemStack stack = this.getStackInSlot(i);
                if (stack.isEmpty() || (stack.getItem() == item.getItem() && ItemStack.areItemStackTagsEqual(stack, item) && stack.getCount() < stack.getMaxStackSize())) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean Generate() {
        storage.consumeEnergy(EnergyPerTick);
        ItemStack item = new ItemStack(RegistryItems.time_particle);
        int amountToGenerate = 1;
        for (int i = 0; i < this.getSizeInventory(); i++) {
            ItemStack stack = this.getStackInSlot(i);
            if (stack.isEmpty()) {
                int amountToAdd = Math.min(amountToGenerate, item.getMaxStackSize());
                this.setInventorySlotContents(i, new ItemStack(item.getItem(), amountToAdd));
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
        nbt.setInteger("Stored",storage.getEnergyStored());
        return nbt;
    }
    @Override
    public void readFromNBT(@NotNull NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        storage.setEnergy(nbt.getInteger("Stored"));
    }
    @Override
    public int getValue(int id) {
        if (id == 1) {
            return this.storage.getEnergyStored();
        }
        if (id == 2) {
            return this.MaxStorage;
        }
        return id;
    }
    @Override
    public String getGuiID() {
        return String.valueOf(RegistryGui.GUI_ENERGY_PARTICLE);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY)
        {
            return (T) storage;
        }
        return super.getCapability(capability, facing);
    }
    @Override
    public boolean hasCapability(net.minecraftforge.common.capabilities.Capability<?> capability, EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY)
        {
            return true;
        }
        return super.hasCapability(capability, facing);
    }
}
