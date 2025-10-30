package com.artiks.torcherinoCe.Block.Energy.MarkerAcceleration;

import com.artiks.torcherinoCe.Block.Core.TileExampleInventoryEnergy;
import com.artiks.torcherinoCe.Block.Energy.Torcherino.RegistryAcceleration;
import com.artiks.torcherinoCe.Register.RegistryGui;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.BlockFluidBase;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TileMarker extends TileExampleInventoryEnergy {
    private final Random rand = new Random();
    private final List<BlockPos> markerPositions = new ArrayList<>();
    private int mode;
    private int maxSpeed = 1;
    private int booleanMode;
    private boolean redstoneSignal, redstoneMode;
    private final int maxModes = 10;
    private final int energyPerTick = 1500;
    private int consume;
    public int getEnergyPerTick() {return energyPerTick;}

    public TileMarker() {
        super(10,10_000_000,Integer.MAX_VALUE,0);
    }

    @Override
    public void update() {
        if (!world.isRemote) {
            CheckRedstoneSignal();
            if ((booleanMode == 1 || redstoneMode)) {
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
                consumeEnergy(consume);
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

    public int getMaxModes() {
        return maxModes;
    }

    public void ToggleWork() {
        booleanMode++;
        if (booleanMode > 3) booleanMode = 0;
    }

    public int getMode() {
        return mode;
    }

    public int getBooleanMode() {
        return booleanMode;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    @Override
    public void getSyncData(Map<String, Object> data) {
        super.getSyncData(data);
        data.put("mode", mode);
        data.put("booleanMode", booleanMode);
        data.put("maxSpeed", maxSpeed);
        data.put("consume", consume);
    }

    @Override
    public void setSyncData(Map<String, Object> data) {
        super.setSyncData(data);
        if (data.containsKey("mode")) {
            this.mode = (int) data.get("mode");
        }
        if (data.containsKey("booleanMode")) {
            this.booleanMode = (int) data.get("booleanMode");
        }
        if (data.containsKey("maxSpeed")) {
            this.maxSpeed = (int) data.get("maxSpeed");
        }
        if (data.containsKey("consume")) {
            this.consume = (int) data.get("consume");
        }
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public @NotNull String getGuiID() {
        return String.valueOf(RegistryGui.GUI_MARKER);
    }

    public @NotNull NBTTagCompound writeToNBT(@NotNull NBTTagCompound nbt) {
        nbt.setInteger("speed", this.mode);
        nbt.setInteger("mode", this.booleanMode);
        nbt.setBoolean("Red", this.redstoneSignal);
        return super.writeToNBT(nbt);
    }

    @Override
    public void readFromNBT(@NotNull NBTTagCompound nbt) {
        this.mode = Math.min(nbt.getInteger("speed"), maxSpeed);
        this.booleanMode = nbt.getInteger("mode");
        this.redstoneSignal = nbt.getBoolean("Red");
        super.readFromNBT(nbt);
    }
}