package com.artiks.torcherinoCe.Block.Energy.FurnaceLuck;

import com.artiks.torcherinoCe.Block.Core.TileExampleInventoryEnergy;
import com.artiks.torcherinoCe.Register.RegistryGui;
import com.artiks.torcherinoCe.utility.IFortuneModule;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

public class TileLuck extends TileExampleInventoryEnergy implements IFortuneModule {

    private final int EnergyPerTick = 25_000;
    private int Progress;
    private final int MaxProgress = 100;
    private int Fortune = 1;
    private boolean isWorking;
    @Override public void setFortune(int fortune) {this.Fortune = fortune;}
    public int getMaxProgress() { return MaxProgress; }
    public int getEnergyPerTick() {return EnergyPerTick;}
    public int getProgress(){return Progress;}
    @Override public int getInventoryStackLimit() {return Integer.MAX_VALUE;}
    public boolean isWorking() {return isWorking;}

    public TileLuck() {
        super(58,1_000_000,Integer.MAX_VALUE,0);
        this.setSlotsForInsert(4, 30);
        this.setSlotsForExtract(31, 57);
    }

    @Override
    public void update() {
        super.update();
        if (world.isRemote) return;
        if (getEnergyStored() < EnergyPerTick) {
            isWorking = false;
            return;
        }
        if (hasValidInputItems()) {
            consumeEnergy(EnergyPerTick);
            isWorking = true;
            if (++Progress >= MaxProgress){
                processAutoSmelting();
                Progress = 0;
            }
        } else {
            isWorking = false;
            Progress = 0;
        }
    }

    private int tryAddToOutputSlots(ItemStack stack) {
        if (stack.isEmpty() || stack.getCount() <= 0) return 0;
        int remaining = stack.getCount();
        for (int slot : invHandler.getSlotsExtract()) {
            ItemStack slotStack = getStackInSlot(slot);
            if (slotStack.isEmpty() || (ItemStack.areItemsEqual(slotStack, stack) && slotStack.getCount() < getInventoryStackLimit())) {
                int space = getInventoryStackLimit() - slotStack.getCount();
                if (space > 0) {
                    int toAdd = Math.min(remaining, space);
                    if (slotStack.isEmpty()) {
                        setInventorySlotContents(slot, stack.copy().splitStack(toAdd));
                    } else {
                        slotStack.grow(toAdd);
                    }
                    remaining -= toAdd;
                    if (remaining <= 0) break;
                }
            }
        }
        return remaining;
    }

    private void processAutoSmelting() {
        for (int inputSlot : invHandler.getSlotsInsert()) {
            ItemStack inputStack = getStackInSlot(inputSlot);
            if (inputStack.isEmpty()) continue;
            ItemStack smeltingResult = getSmeltingResult(inputStack);
            if (!smeltingResult.isEmpty()) {
                processStackMode(inputSlot, smeltingResult);
            }
        }
    }

    private void processStackMode(int inputSlot, ItemStack result) {
        ItemStack inputStack = getStackInSlot(inputSlot);
        if (inputStack.isEmpty()) return;
        int totalResultCount = inputStack.getCount() * result.getCount() * Fortune;
        ItemStack totalResult = result.copy();
        totalResult.setCount(totalResultCount);
        int remaining = tryAddToOutputSlots(totalResult);
        if (remaining > 0) {
            int processedCount = inputStack.getCount() - (int) Math.ceil((double) remaining / (result.getCount() * Fortune));
            if (processedCount > 0) {
                totalResultCount = processedCount * result.getCount() * Fortune;
                totalResult.setCount(totalResultCount);
                int newRemaining = tryAddToOutputSlots(totalResult);
                if (newRemaining == 0) {
                    inputStack.shrink(processedCount);
                    if (inputStack.getCount() <= 0) {
                        setInventorySlotContents(inputSlot, ItemStack.EMPTY);
                    }
                }
            }
        } else {
            setInventorySlotContents(inputSlot, ItemStack.EMPTY);
        }
    }

    private boolean hasValidInputItems() {
        for (int slot : invHandler.getSlotsInsert()) {
            ItemStack stack = getStackInSlot(slot);
            if (!stack.isEmpty() && !getSmeltingResult(stack).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private ItemStack getSmeltingResult(ItemStack inputStack) {
        ItemStack result = FurnaceRecipes.instance().getSmeltingResult(inputStack);
        return result.isEmpty() ? ItemStack.EMPTY : result.copy();
    }

    @Override
    public @NotNull NBTTagCompound writeToNBT(@NotNull NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("fortune", Fortune);
        return nbt;
    }

    @Override
    public void readFromNBT(@NotNull NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        Fortune = nbt.getInteger("fortune");
    }

    @Override
    public @NotNull AxisAlignedBB getRenderBoundingBox() {
        return INFINITE_EXTENT_AABB;
    }

    @Override
    public @NotNull String getGuiID() {
        return String.valueOf(RegistryGui.GUI_LUCK);
    }

    @Override
    public void getSyncData(Map<String, Object> data) {
        super.getSyncData(data);
        data.put("Progress", Progress);
        data.put("work",isWorking);
    }

    @Override
    public void setSyncData(Map<String, Object> data) {
        super.setSyncData(data);
        if(data.containsKey("Progress")){
            this.Progress = (int) data.get("Progress");
        }
        if(data.containsKey("work")){
            this.isWorking = (boolean) data.get("work");
        }
    }
}