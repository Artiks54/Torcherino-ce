package com.ariks.torcherino.Block.RfMolecular;

import com.ariks.torcherino.Block.Core.TileExampleInventory;
import com.ariks.torcherino.Register.RegistryGui;
import com.ariks.torcherino.util.Sound;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

public class TileRfMolecular extends TileExampleInventory implements ITickable {
    private final EnergyStorageMolecular storage;
    private int currentRecipeID = -1;
    public long energyReceived;
    public long energyRequired;
    public long energyCollected;
    public boolean work;
    private final Sound sound = new Sound();
    public TileRfMolecular() {
        super(2);
        this.setSlotsForInsert(0);
        this.setSlotsForExtract(1);
        this.storage = new EnergyStorageMolecular(Integer.MAX_VALUE,0,Integer.MAX_VALUE);
    }
    private ArrayList<MolecularRecipe> currentRecipe() {
        return MolecularRecipe.getRecipes();
    }
    public void findRecipe() {
        if (currentRecipeID >= 0) return;
        ItemStack inputStack = getStackInSlot(0);
        ItemStack outputStack = getStackInSlot(1);
        if (inputStack.isEmpty()) return;
        ArrayList<MolecularRecipe> recipes = currentRecipe();
        for (int i = 0; i < recipes.size(); i++) {
            MolecularRecipe recipe = recipes.get(i);
            if (recipe.matches(inputStack, outputStack)) {
                currentRecipeID = i;
                work = true;
                energyRequired = recipe.getEnergy();
                decrStackSize(0, recipe.getInput().getCount());
                this.UpdateTile();
                return;
            }
        }
    }
    private void Work() {
        if (!work || !canOutputRecipeResult()) return;
        storage.setCanReceiveEnergy(true);
        energyReceived = storage.getEnergyStored();
        if (energyReceived > 0) {
            storage.consumeEnergy((int) energyReceived);
            energyCollected += energyReceived;
            this.UpdateTile();
            this.RecipeOut();
        }
    }
    private void Reset() {
        energyCollected = 0;
        energyRequired = 0;
        energyReceived = 0;
        currentRecipeID = -1;
        storage.setCanReceiveEnergy(false);
        work = false;
    }
    private boolean canOutputRecipeResult() {
        if (currentRecipeID < 0) return false;
        ItemStack outputStack = currentRecipe().get(currentRecipeID).getRecipeOutput();
        ItemStack currentOutputStack = getStackInSlot(1);
        if (currentOutputStack.isEmpty()) return true;
        if (!currentOutputStack.isItemEqual(outputStack) || !ItemStack.areItemStackTagsEqual(currentOutputStack, outputStack)) return false;
        return currentOutputStack.getCount() + outputStack.getCount() <= currentOutputStack.getMaxStackSize();
    }
    private void RecipeOut() {
        if (energyCollected < energyRequired) return;
        ItemStack outputStack = currentRecipe().get(currentRecipeID).getRecipeOutput();
        ItemStack currentOutputStack = getStackInSlot(1);
        if (currentOutputStack.isEmpty()) {
            setInventorySlotContents(1, outputStack.copy());
        } else {
            currentOutputStack.grow(outputStack.getCount());
        }
        this.Reset();
    }
    @Override
    public void update() {
        if (!world.isRemote) {
            this.findRecipe();
            this.Work();
        } else {
            sound.play(world,pos,work,Sound.SOUND_MOLECULAR);
        }
    }
    @Override
    public int getValue(int id) {
        if(id == 1){
            return currentRecipeID;
        }
        return id;
    }
    @Override
    public @NotNull NBTTagCompound writeToNBT(@NotNull NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setLong("Stored",energyCollected);
        nbt.setLong("Er",energyRequired);
        nbt.setLong("Rt",energyReceived);
        nbt.setInteger("RecipeID",currentRecipeID);
        nbt.setBoolean("Work",work);
        return nbt;
    }
    @Override
    public void readFromNBT(@NotNull NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.energyRequired = nbt.getLong("Er");
        this.energyReceived = nbt.getLong("Rt");
        this.energyCollected = nbt.getLong("Stored");
        this.currentRecipeID = nbt.getInteger("RecipeID");
        this.work = nbt.getBoolean("Work");
        this.storage.setCanReceiveEnergy(work);
    }
    @Override
    public @NotNull AxisAlignedBB getRenderBoundingBox() {
        return INFINITE_EXTENT_AABB;
    }
    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(@NotNull Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY)
        {
            return (T) storage;
        }
        return super.getCapability(capability, facing);
    }
    @Override
    public boolean hasCapability(@NotNull Capability<?> capability, EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY)
        {
            return true;
        }
        return super.hasCapability(capability, facing);
    }
    @Override
    public String getGuiID() {
        return String.valueOf(RegistryGui.GUI_RF_MOLECULAR);
    }
}