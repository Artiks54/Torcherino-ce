package com.artiks.torcherinoCe.Block.Energy.Molecular;

import com.artiks.torcherinoCe.Block.Core.TileExampleInventory;
import com.artiks.torcherinoCe.Register.RegistryGui;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.Map;

public class TileRfMolecular extends TileExampleInventory implements ITickable {
    private final EnergyStorageMolecular storage;
    private int energyPerTick;
    private long energyRequired;
    private long energyCollected;
    private MolecularRecipe currentRecipe;
    private int currentRecipeID =-1;
    private int textureId = 0;
    private boolean redstoneSignal;
    private int booleanMode;
    private int stackMode = 0;
    private int numRecipes = 1;
    private boolean isWorking;
    private int noRecipeTicks = 0;
    private static final int NO_RECIPE_TIMEOUT = 10;

    public TileRfMolecular() {
        super(4);
        this.setSlotsForInsert(0);
        this.setSlotsForExtract(3);
        this.storage = new EnergyStorageMolecular(Integer.MAX_VALUE, 0,0);
    }

    public void setRedstoneSignal(boolean redstoneSignal) {
        this.redstoneSignal = redstoneSignal;
    }

    public void ToggleWork() {
        booleanMode++;
        energyPerTick = 0;
        if (booleanMode > 3) {
            booleanMode = 0;
        }
    }

    public void ToggleStackMode(){
        stackMode++;
        if(stackMode > 1){
            stackMode = 0;
        }
    }

    private void findRecipe() {
        ItemStack input = getStackInSlot(0);
        if (input.isEmpty()) {
            noRecipeTicks++;
            return;
        }
        for (MolecularRecipe recipe : MolecularRecipe.getRecipes()) {
            if (recipe.matches(input) && hasValidOutputSpace()) {
                currentRecipe = recipe;
                currentRecipeID = MolecularRecipe.getRecipes().indexOf(recipe);
                numRecipes = (stackMode == 1) ? calculateNumRecipes(recipe) : 1;
                energyRequired = recipe.getEnergy() * numRecipes;
                decrStackSize(0, recipe.getInput().getCount() * numRecipes);
                noRecipeTicks = 0;
                return;
            }
        }
    }

    private int calculateNumRecipes(MolecularRecipe recipe) {
        int maxFromInput = getStackInSlot(0).getCount() / recipe.getInput().getCount();
        ItemStack currentOutput = getStackInSlot(3);
        int maxStackSize = currentOutput.isEmpty() ? recipe.getRecipeOutput().getMaxStackSize() : currentOutput.getMaxStackSize() - currentOutput.getCount();
        return Math.min(maxFromInput, maxStackSize / recipe.getRecipeOutput().getCount());
    }

    private void processRecipe() {
        storage.setMaxCapacity(Integer.MAX_VALUE);
        int energyConsumed = storage.consumeAllEnergy();
        if (energyConsumed > 0) {
            energyCollected += energyConsumed;
            energyPerTick = energyConsumed;
            if (energyCollected >= energyRequired) {
                completeRecipe();
            }
        }
    }

    private void completeRecipe() {
        ItemStack outputStack = currentRecipe.getRecipeOutput();
        if (outputStack.isEmpty()) return;
        int outputCount = outputStack.getCount() * numRecipes;
        ItemStack currentOutput = getStackInSlot(3);
        if (currentOutput.isEmpty()) {
            ItemStack newStack = outputStack.copy();
            newStack.setCount(outputCount);
            setInventorySlotContents(3, newStack);
        } else {
            currentOutput.grow(outputCount);
        }
        reset();
    }

    private void reset() {
        energyCollected = 0;
        energyRequired = 0;
        currentRecipeID = -1;
        energyPerTick = 0;
        storage.setMaxCapacity(0);
        currentRecipe = null;
        numRecipes = 1;
        noRecipeTicks = 0;
    }

    public long getEnergyCollected() {
        return energyCollected;
    }

    public void setEnergyCollected(long energyCollected) {
        this.energyCollected = energyCollected;
    }

    public boolean getWork() {
        return switch (booleanMode) {
            case 1 -> true;
            case 2 -> redstoneSignal;
            case 3 -> !redstoneSignal;
            default -> false;
        };
    }

    @Override
    public void update() {
        if (!world.isRemote) {
            super.update();
            boolean wasWorking = isWorking;
            if (currentRecipe == null) {
                findRecipe();
            }
            if (getWork() && currentRecipe != null) {
                this.processRecipe();
                isWorking = true;
                noRecipeTicks = 0;
            } else {
                storage.setMaxCapacity(0);
                if (currentRecipe == null) {
                    noRecipeTicks++;
                } else {
                    noRecipeTicks = 0;
                }
                if (noRecipeTicks > NO_RECIPE_TIMEOUT) {
                    isWorking = false;
                }
            }
            if (wasWorking != isWorking) {
                world.notifyBlockUpdate(pos,world.getBlockState(pos),world.getBlockState(pos),3);
            }
        }
    }

    @Override
    public void getSyncData(Map<String, Object> data) {
        data.put("currentRecipeID", currentRecipeID);
        data.put("textureId", textureId);
        data.put("booleanMode", booleanMode);
        data.put("energyPerTick", energyPerTick);
        data.put("numRecipes", numRecipes);
        data.put("stackMode",stackMode);
        data.put("energyRequired",energyRequired);
        data.put("energyCollected",energyCollected);
        data.put("isWorking",isWorking);
    }

    @Override
    public void setSyncData(Map<String, Object> data) {
        if (data.containsKey("isWorking")) {
            this.isWorking = (boolean) data.get("isWorking");
        }
        if (data.containsKey("energyRequired")) {
            this.energyRequired = (long) data.get("energyRequired");
        }
        if (data.containsKey("energyCollected")) {
            this.setEnergyCollected((long) data.get("energyCollected"));
        }
        if (data.containsKey("currentRecipeID")) {
            this.currentRecipeID = (int) data.get("currentRecipeID");
        }
        if (data.containsKey("textureId")) {
            this.textureId = (int) data.get("textureId");
        }
        if (data.containsKey("booleanMode")) {
            this.booleanMode = (int) data.get("booleanMode");
        }
        if (data.containsKey("energyPerTick")) {
            this.energyPerTick = (int) data.get("energyPerTick");
        }
        if (data.containsKey("numRecipes")) {
            this.numRecipes = (int) data.get("numRecipes");
        }
        if (data.containsKey("stackMode")) {
            this.stackMode = (int) data.get("stackMode");
        }
    }

    public int getCurrentRecipeID() {
        return currentRecipeID;
    }

    public int getTextureId() {
        return textureId;
    }

    public int getBooleanMode() {
        return booleanMode;
    }

    public int getEnergyPerTick() {
        return energyPerTick;
    }

    public int getNumRecipes() {
        return numRecipes;
    }

    public int getStackMode() {
        return stackMode;
    }

    public boolean isWorking() {return isWorking;}

    public void SwitchTexture() {
        textureId++;
        if (textureId > 8) {
            textureId = 0;
        }
    }

    @Override
    public @NotNull NBTTagCompound writeToNBT(@NotNull NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("RecipeID",currentRecipeID);
        nbt.setLong("Stored",energyCollected);
        nbt.setLong("Er",energyRequired);
        nbt.setInteger("textureId",textureId);
        nbt.setBoolean("Red",redstoneSignal);
        nbt.setInteger("mode",booleanMode);
        nbt.setInteger("numRecipes",numRecipes);
        nbt.setInteger("stackMode",stackMode);
        nbt.setBoolean("isWorking",isWorking);
        return nbt;
    }

    @Override
    public void readFromNBT(@NotNull NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.energyRequired = nbt.getLong("Er");
        this.energyCollected = nbt.getLong("Stored");
        this.textureId = nbt.getInteger("textureId");
        this.numRecipes = nbt.getInteger("numRecipes");
        this.redstoneSignal = nbt.getBoolean("Red");
        this.booleanMode = nbt.getInteger("mode");
        this.stackMode = nbt.getInteger("stackMode");
        this.currentRecipeID = nbt.getInteger("RecipeID");
        this.isWorking = nbt.getBoolean("isWorking");
        if (currentRecipeID != -1) {
            List<MolecularRecipe> recipes = MolecularRecipe.getRecipes();
            if (currentRecipeID >= 0 && currentRecipeID < recipes.size()) {
                currentRecipe = recipes.get(currentRecipeID);
                storage.setMaxCapacity(Integer.MAX_VALUE);
            } else {
                this.reset();
            }
        }
    }


    @Override
    public @NotNull AxisAlignedBB getRenderBoundingBox() {
        return INFINITE_EXTENT_AABB;
    }

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
        if (capability == CapabilityEnergy.ENERGY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public @NotNull String getGuiID() {
        return String.valueOf(RegistryGui.GUI_RF_MOLECULAR);
    }
}