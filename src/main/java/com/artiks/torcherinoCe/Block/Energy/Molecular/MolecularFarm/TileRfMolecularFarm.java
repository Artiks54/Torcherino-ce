package com.artiks.torcherinoCe.Block.Energy.Molecular.MolecularFarm;

import com.artiks.torcherinoCe.Block.Core.TileExampleInventory;
import com.artiks.torcherinoCe.Block.Energy.Molecular.EnergyStorageMolecular;
import com.artiks.torcherinoCe.Register.RegistryGui;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TileRfMolecularFarm extends TileExampleInventory implements ITickable {
    private final EnergyStorageMolecular storage;
    private long totalEnergyRequired;
    private long totalEnergyCollected;
    private int EnergyPerTick;
    private boolean isWorking;
    private int noRecipeTicks = 0;
    private int activeCount;
    private final int NO_RECIPE_TIMEOUT = 10;
    private final RecipeProgress[] recipeProgresses;
    private final Map<Item, List<MolecularRecipeFarm>> RECIPE_MAP;

    public TileRfMolecularFarm() {
        super(26);
        RECIPE_MAP = createRecipeMap();
        setSlotsForInsert(0, 11);
        setSlotsForExtract(13, 24);
        storage = new EnergyStorageMolecular(Integer.MAX_VALUE,0,0);
        recipeProgresses = new RecipeProgress[12];
        List<Integer> insertSlots = invHandler.getSlotsInsert();
        for (int i = 0; i < recipeProgresses.length; i++) {
            recipeProgresses[i] = new RecipeProgress(insertSlots.get(i));
        }
    }

    private Map<Item, List<MolecularRecipeFarm>> createRecipeMap() {
        Map<Item, List<MolecularRecipeFarm>> map = new HashMap<>();
        for (MolecularRecipeFarm recipe : MolecularRecipeFarm.getRecipes()) {
            Item inputItem = recipe.getInput().getItem();
            map.computeIfAbsent(inputItem, k -> new ArrayList<>()).add(recipe);
        }
        return map;
    }

    private static class RecipeProgress {
        MolecularRecipeFarm recipe;
        long energyRequired;
        int numRecipes = 1;
        int recipeId = -1;
        final int inputSlotIndex;
        RecipeProgress(int inputSlotIndex) {
            this.inputSlotIndex = inputSlotIndex;
        }
        void reset() {
            recipe = null;
            energyRequired = 0;
            numRecipes = 1;
            recipeId = -1;
        }
    }

    private void findRecipes() {
        boolean foundAnyRecipe = false;
        totalEnergyRequired = 0;
        for (RecipeProgress progress : recipeProgresses) {
            if (progress.recipe != null) {
                foundAnyRecipe = true;
                totalEnergyRequired += progress.energyRequired;
                continue;
            }
            ItemStack input = getStackInSlot(progress.inputSlotIndex);
            if (input.isEmpty()) {
                continue;
            }
            List<MolecularRecipeFarm> possibleRecipes = RECIPE_MAP.get(input.getItem());
            if (possibleRecipes != null) {
                for (MolecularRecipeFarm recipe : possibleRecipes) {
                    if (recipe.matches(input)) {
                        if (!canFitRecipeOutput(recipe, input)) {
                            continue;
                        }
                        progress.recipe = recipe;
                        progress.recipeId = MolecularRecipeFarm.getRecipes().indexOf(recipe);
                        progress.numRecipes = calculateNumRecipes(input, recipe);
                        progress.energyRequired = recipe.getEnergy() * progress.numRecipes;
                        totalEnergyRequired += progress.energyRequired;
                        foundAnyRecipe = true;
                        break;
                    }
                }
            }
        }
        noRecipeTicks = foundAnyRecipe ? 0 : noRecipeTicks + 1;
        isWorking = foundAnyRecipe;
    }

    private boolean canFitRecipeOutput(MolecularRecipeFarm recipe, ItemStack input) {
        ItemStack outputStack = recipe.getRecipeOutput();
        if (outputStack.isEmpty()) {
            return true;
        }
        int numRecipes = calculateNumRecipes(input, recipe);
        ItemStack resultStack = outputStack.copy();
        resultStack.setCount(outputStack.getCount() * numRecipes);
        return hasEnoughOutputSpace(resultStack);
    }

    private int calculateNumRecipes(ItemStack input, MolecularRecipeFarm recipe) {
        return input.getCount() / recipe.getInput().getCount();
    }

    private void processRecipes() {
        if (totalEnergyRequired == 0) {
            storage.setMaxCapacity(0);
            storage.setEnergy(0);
            EnergyPerTick = 0;
            return;
        }
        storage.setMaxCapacity(Integer.MAX_VALUE);
        int energyConsumed = storage.consumeAllEnergy();
        EnergyPerTick = energyConsumed;
        if (energyConsumed > 0) {
            totalEnergyCollected += energyConsumed;
            if (totalEnergyCollected >= totalEnergyRequired) {
                completeAllRecipes();
            }
        }
    }

    private void completeAllRecipes() {
        for (RecipeProgress progress : recipeProgresses) {
            if (progress.recipe != null) {
                ItemStack outputStack = progress.recipe.getRecipeOutput();
                if (!outputStack.isEmpty()) {
                    ItemStack resultStack = outputStack.copy();
                    resultStack.setCount(outputStack.getCount() * progress.numRecipes);
                    addInventoryItemOutput(resultStack);
                }
                progress.reset();
            }
        }
        totalEnergyCollected = 0;
        totalEnergyRequired = 0;
        EnergyPerTick = 0;
    }

    @Override
    public void update() {
        super.update();
        if (!world.isRemote) {
            boolean wasWorking = isWorking;
            findRecipes();
            if (noRecipeTicks > NO_RECIPE_TIMEOUT) {
                isWorking = false;
                totalEnergyCollected = 0;
                totalEnergyRequired = 0;
                EnergyPerTick = 0;
            } else {
                processRecipes();
                activeCount = getActiveRecipeCount();
            }
            if (wasWorking != isWorking) {
                world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
            }
        }
    }

    private int getActiveRecipeCount() {
        int count = 0;
        for (RecipeProgress progress : recipeProgresses) {
            if (progress.recipe != null) {
                count++;
            }
        }
        return count;
    }

    public int getEnergyPerTick() {
        return EnergyPerTick;
    }

    public boolean isWorking() {
        return isWorking;
    }

    @Override
    public @NotNull NBTTagCompound writeToNBT(@NotNull NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setLong("stored", totalEnergyCollected);
        nbt.setLong("required", totalEnergyRequired);
        nbt.setBoolean("working", isWorking);
        nbt.setInteger("noRecipeTicks", noRecipeTicks);
        NBTTagList progressList = new NBTTagList();
        for (int i = 0; i < recipeProgresses.length; i++) {
            RecipeProgress progress = recipeProgresses[i];
            if (progress.recipe != null) {
                NBTTagCompound progressTag = new NBTTagCompound();
                progressTag.setInteger("index", i);
                progressTag.setInteger("recipeId", progress.recipeId);
                progressTag.setLong("energyRequired", progress.energyRequired);
                progressTag.setInteger("numRecipes", progress.numRecipes);
                progressList.appendTag(progressTag);
            }
        }
        nbt.setTag("recipes", progressList);
        return nbt;
    }

    @Override
    public void readFromNBT(@NotNull NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        totalEnergyRequired = nbt.getLong("required");
        totalEnergyCollected = nbt.getLong("stored");
        isWorking = nbt.getBoolean("working");
        noRecipeTicks = nbt.getInteger("noRecipeTicks");
        for (RecipeProgress progress : recipeProgresses) {
            progress.reset();
        }
        NBTTagList progressList = nbt.getTagList("recipes", 10);
        List<MolecularRecipeFarm> allRecipes = MolecularRecipeFarm.getRecipes();
        for (int i = 0; i < progressList.tagCount(); i++) {
            NBTTagCompound progressTag = progressList.getCompoundTagAt(i);
            int index = progressTag.getInteger("index");
            int recipeId = progressTag.getInteger("recipeId");
            long energyRequired = progressTag.getLong("energyRequired");
            int numRecipes = progressTag.getInteger("numRecipes");
            if (index >= 0 && index < recipeProgresses.length &&
                    recipeId >= 0 && recipeId < allRecipes.size()) {
                RecipeProgress progress = recipeProgresses[index];
                progress.recipe = allRecipes.get(recipeId);
                progress.recipeId = recipeId;
                progress.energyRequired = energyRequired;
                progress.numRecipes = numRecipes;
            }
        }
    }

    @Override
    public void getSyncData(Map<String, Object> data) {
        data.put("energyRequired",totalEnergyRequired);
        data.put("energyCollected",totalEnergyCollected);
        data.put("isWorking",isWorking);
        data.put("activeCount",activeCount);
        data.put("EnergyPerTick",EnergyPerTick);
    }

    @Override
    public void setSyncData(Map<String, Object> data) {
        if (data.containsKey("isWorking")) {
            this.isWorking = (boolean) data.get("isWorking");
        }
        if (data.containsKey("energyRequired")) {
            this.totalEnergyRequired = (long) data.get("energyRequired");
        }
        if (data.containsKey("energyCollected")) {
            this.totalEnergyCollected = (long) data.get("energyCollected");
        }
        if (data.containsKey("activeCount")) {
            this.activeCount = (int) data.get("activeCount");
        }
        if (data.containsKey("EnergyPerTick")) {
            this.EnergyPerTick = (int) data.get("EnergyPerTick");
        }
    }

    public long getEnergyCollected() {return totalEnergyCollected;}

    public long getTotalEnergyRequired() {return totalEnergyRequired;}

    public int getActiveCount() {return activeCount;}

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
        return String.valueOf(RegistryGui.GUI_RF_MOLECULAR_FARM);
    }
}