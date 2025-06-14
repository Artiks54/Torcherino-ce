package com.ariks.torcherinoCe.Block.RfMolecular;

import com.ariks.torcherinoCe.Block.Core.TileExampleInventory;
import com.ariks.torcherinoCe.Register.RegistryGui;
import com.ariks.torcherinoCe.Register.RegistryNetwork;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class    TileRfMolecular extends TileExampleInventory implements ITickable {
    private final EnergyStorageMolecular storage;
    protected NetworkRegistry.TargetPoint packetTargetPoint;
    private int energyPerTick;
    private long energyRequired;
    private long energyCollected;
    private MolecularRecipe currentRecipe;
    private int currentRecipeID =-1;
    private int textureId = 0;
    private boolean redstoneSignal;
    private int booleanMode;

    public TileRfMolecular() {
        super(2);
        this.setSlotsForInsert(0);
        this.setSlotsForExtract(1);
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
    public void findRecipe() {
        ItemStack input = getStackInSlot(0);
        if (input.isEmpty()) return;
        List<MolecularRecipe> recipes = MolecularRecipe.getRecipes();
        for (int i = 0; i < recipes.size(); i++) {
            MolecularRecipe recipe = recipes.get(i);
            if (recipe.matches(input, getStackInSlot(1))) {
                if (canOutputRecipeResult(recipe)) {
                    currentRecipe = recipe;
                    currentRecipeID = i;
                    energyRequired = recipe.getEnergy();
                    decrStackSize(0, recipe.getInput().getCount());
                    return;
                }
            }
        }
    }
    private void processRecipe() {
            storage.setMaxCapacity(Integer.MAX_VALUE);
            int energyConsumed = storage.consumeAllEnergy();
            if (energyConsumed > 0) {
                energyCollected += energyConsumed;
                energyPerTick = energyConsumed;
                RegistryNetwork.network.sendToAllAround(new PacketMolecular(this.pos, energyCollected), packetTargetPoint);
                if (energyCollected >= energyRequired) {
                    completeRecipe();
            }
        }
    }
    private void completeRecipe() {
        ItemStack outputStack = currentRecipe.getRecipeOutput();
        if (outputStack.isEmpty()) return;
        ItemStack currentOutputStack = getStackInSlot(1);
        if (currentOutputStack.isEmpty()) {
            setInventorySlotContents(1, outputStack.copy());
        } else {
            currentOutputStack.grow(outputStack.getCount());
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
    }
    private boolean canOutputRecipeResult(MolecularRecipe recipe) {
        if (recipe == null) return false;
        ItemStack result = recipe.getRecipeOutput();
        if (result.isEmpty()) return false;
        ItemStack currentOutput = getStackInSlot(1);
        if (currentOutput.isEmpty()) return true;
        if (!currentOutput.isItemEqual(result) || !ItemStack.areItemStackTagsEqual(currentOutput, result)) {
            return false;
        }
        return currentOutput.getCount() + result.getCount() <= currentOutput.getMaxStackSize();
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
            if(currentRecipe == null) {
                findRecipe();
            }
            if (getWork() && currentRecipe !=null){
                this.processRecipe();
            } else {
                storage.setMaxCapacity(0);
            }
        }
    }
    public int getBooleanMode() {
        return booleanMode;
    }
    @Override
    public int getValue(int id) {
        if(id == 1) return currentRecipeID;
        if(id == 2) return textureId;
        if(id == 3) return booleanMode;
        if(id == 4) return energyPerTick;
        return id;
    }
    @Override
    public void setValue(int id, int value) {
        if(id == 1) this.currentRecipeID = value;
        if(id == 2) this.textureId = value;
        if(id == 3) this.booleanMode = value;
        if(id == 4) this.energyPerTick = value;
    }
    @Override
    public int[] getValueList() {
        return new int[]{1,2,3,4};
    }
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
        return nbt;
    }
    @Override
    public void readFromNBT(@NotNull NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.energyRequired = nbt.getLong("Er");
        this.energyCollected = nbt.getLong("Stored");
        this.textureId = nbt.getInteger("textureId");
        this.redstoneSignal = nbt.getBoolean("Red");
        this.booleanMode = nbt.getInteger("mode");
        this.currentRecipeID = nbt.getInteger("RecipeID");
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
    public void onLoad() {
        this.world.getBlockState(this.pos);
        if (!this.world.isRemote) {
            this.packetTargetPoint = new NetworkRegistry.TargetPoint(this.world.provider.getDimension(), this.pos.getX(), this.pos.getY(), this.pos.getZ(),64);
        }
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