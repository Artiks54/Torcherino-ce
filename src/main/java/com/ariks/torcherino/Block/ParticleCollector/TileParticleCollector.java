package com.ariks.torcherino.Block.ParticleCollector;

import com.ariks.torcherino.Block.Core.TileExampleInventory;
import com.ariks.torcherino.Register.RegistryGui;
import com.ariks.torcherino.Register.RegistryItems;
import com.ariks.torcherino.util.Config;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import org.jetbrains.annotations.NotNull;

public class TileParticleCollector extends TileExampleInventory implements ITickable {
    private int Amount = 1;
    private int AddProgress = 1;
    private int Progress;
    private int MaxProgress = Config.RequiredGeneratorParticle;
    public int percent;
    public TileParticleCollector(){
        super(6);
        this.setSlotsForInsert(0,1);
        this.setSlotsForExtract(2,5);
    }
    @Override
    public void update() {
        if (!world.isRemote) {
            this.Work();
        }
    }
    private void Work() {
        if (this.CanGenerate()) {
            Progress += AddProgress;
            this.UpdateTile();
            if (Progress >= MaxProgress) {
                if (this.Generate()) {
                    Progress = 0;
                }
            }
        } else {
            Progress = 0;
            this.UpdateTile();
        }
        percent = (Progress * 100) / MaxProgress;
    }
    private boolean CanGenerate() {
        ItemStack item = new ItemStack(RegistryItems.time_particle);
        boolean hasSpace = false;
        for (int i = 2; i < this.getSizeInventory(); i++) {
            ItemStack stack = this.getStackInSlot(i);
            if (stack.isEmpty() || (stack.getItem() == item.getItem() && ItemStack.areItemStackTagsEqual(stack, item) && stack.getCount() < stack.getMaxStackSize())) {
                hasSpace = true;
                break;
            }
        }
        return hasSpace;
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
        for (int i = 2; i < this.getSizeInventory(); i++) {
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
    public int getValue(int id) {
        if (id == 1) {
            return this.Progress;
        }
        if (id == 2) {
            return this.MaxProgress;
        }
        return id;
    }
    @Override
    public String getGuiID() {
        return String.valueOf(RegistryGui.GUI_PARTICLE_COLLECTOR);
    }
}