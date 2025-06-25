package com.ariks.torcherinoCe.Block.MarkerAcceleration;

import com.ariks.torcherinoCe.Block.Core.TileExampleInventory;
import com.ariks.torcherinoCe.Block.Torcherino.RegistryAcceleration;
import com.ariks.torcherinoCe.Register.RegistryGui;
import com.ariks.torcherinoCe.Register.RegistryItems;
import com.ariks.torcherinoCe.utility.EnergyStorage;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fluids.BlockFluidBase;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TileMarker extends TileExampleInventory implements ITickable {
    private final Random rand = new Random();
    private final List<BlockPos> markerPositions = new ArrayList<>();
    private int mode;
    private int maxSpeed = 1;
    private int booleanMode;
    private boolean redstoneSignal, redstoneMode;
    private final EnergyStorage storage;
    private final int maxModes = 10;
    private final int maxEnergyStorage = 10_000_000;
    private final int energyPerTick = 1500;
    private int consume;

    public TileMarker() {
        super(9);
        storage = new EnergyStorage(maxEnergyStorage,Integer.MAX_VALUE,0);
    }
    @Override
    public void update() {
        if (!world.isRemote) {
            CheckRedstoneSignal();
            getSpeed();
            if((booleanMode == 1 || redstoneMode)) {
                work();
            }
        }
    }
    private void CheckRedstoneSignal() {
        redstoneMode = (booleanMode == 2 && redstoneSignal) || (booleanMode == 3 && !redstoneSignal);
    }
    public void setRedstoneSignal(boolean redstoneSignal) {
        this.redstoneSignal = redstoneSignal;
    }
    private void work() {
        markerPositions.clear();
        for (int i = 0; i < 8; i++) {
            ItemStack marker = this.getStackInSlot(i);
            if (!marker.isEmpty() && marker.hasTagCompound()) {
                NBTTagCompound nbt = marker.getTagCompound();
                if (nbt != null && nbt.hasKey("x") && nbt.hasKey("y") && nbt.hasKey("z")) {
                    BlockPos pos = new BlockPos(nbt.getInteger("x"), nbt.getInteger("y"), nbt.getInteger("z"));
                    if (!markerPositions.contains(pos)) {
                        markerPositions.add(pos);
                    }
                }
            }
        }
        if (markerPositions.isEmpty()) {
            consume = 0;
            return;
        }
        for (BlockPos pos : markerPositions) {
            if (world.isBlockLoaded(pos)) {
                accelerationTick(pos);
                consume = (energyPerTick * markerPositions.size());
                storage.consumeEnergy(consume);
            }
        }
    }
    private void accelerationTick(BlockPos pos) {
        IBlockState blockState = world.getBlockState(pos);
        Block block = blockState.getBlock();
        if (block instanceof BlockFluidBase || RegistryAcceleration.isBlockBlacklisted(blockState)) {
            return;
        }
        if (block.getTickRandomly()) {
            int speedBase = mode * maxSpeed;
            for (int i = 0; i < speedBase; i++) {
                if (world.getBlockState(pos) != blockState) {
                    break;
                }
                block.updateTick(world, pos, blockState, rand);
            }
        }
        if (block.hasTileEntity(blockState)) {
            TileEntity tile = world.getTileEntity(pos);
            if (tile == null || tile.isInvalid()) {
                return;
            }
            if (RegistryAcceleration.isTileBlacklisted(tile.getClass())) {
                return;
            }
            int speedBase = mode * maxSpeed;
            for (int i = 0; i < speedBase; i++) {
                if (tile.isInvalid()) {
                    break;
                }
                if (tile instanceof ITickable) {
                    ((ITickable) tile).update();
                }
            }
        }
    }
    public int getMaxEnergyStorage() {
        return storage.getMaxEnergyStored();
    }
    public int getMaxModes() {
        return maxModes;
    }
    public void ToggleWork() {
        booleanMode++;
        if (booleanMode > 3) booleanMode = 0;
    }
    @Override
    public int[] getValueList() {
        return new int[]{1,2,3,4,5};
    }
    @Override
    public int getValue(int id) {
        if(id == 1){
            return this.mode;
        }
        if(id == 2){
            return this.booleanMode;
        }
        if(id == 3){
            return this.maxSpeed;
        }
        if(id == 4){
            return this.storage.getEnergyStored();
        }
        if(id == 5){
            return consume;
        }
        return id;
    }
    @Override
    public void setValue(int id, int value) {
        if(id == 1){
            this.mode = value;
        }
        if(id == 2){
            this.booleanMode = value;
        }
        if(id == 3){
            this.maxSpeed = value;
        }
        if(id == 4){
            this.storage.setEnergy(value);
        }
        if(id == 5){
            this.consume = value;
        }
    }
    private void getSpeed(){
        ItemStack stack = this.getStackInSlot(8);
        if(stack.getItem() == RegistryItems.upgrade_gps){
            maxSpeed = 9;
        }
        if(stack.getItem() == RegistryItems.upgrade_gps_2){
            maxSpeed = 81;
        }
        if(stack.isEmpty()){
            maxSpeed = 1;
        }
    }
    @Override
    public @NotNull String getGuiID() {
        return String.valueOf(RegistryGui.GUI_MARKER);
    }
    public @NotNull NBTTagCompound writeToNBT(@NotNull NBTTagCompound nbt) {
        nbt.setInteger("speed", this.mode);
        nbt.setInteger("energy",storage.getEnergyStored());
        nbt.setInteger("mode", this.booleanMode);
        nbt.setBoolean("Red", this.redstoneSignal);
        return super.writeToNBT(nbt);
    }
    @Override
    public void readFromNBT(@NotNull NBTTagCompound nbt) {
        this.storage.setEnergy(nbt.getInteger("energy"));
        this.mode = Math.min(nbt.getInteger("speed"),maxSpeed);
        this.booleanMode = nbt.getInteger("mode");
        this.redstoneSignal = nbt.getBoolean("Red");
        super.readFromNBT(nbt);
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