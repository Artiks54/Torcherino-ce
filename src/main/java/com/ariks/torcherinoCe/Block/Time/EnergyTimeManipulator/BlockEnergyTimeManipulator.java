package com.ariks.torcherinoCe.Block.Time.EnergyTimeManipulator;

import com.ariks.torcherinoCe.Block.Time.TimeBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlockEnergyTimeManipulator extends TimeBlock {
    public BlockEnergyTimeManipulator(String name) {
        super(name);
    }
    @Override
    public @NotNull AxisAlignedBB getBoundingBox(@NotNull IBlockState state, @NotNull IBlockAccess source, @NotNull BlockPos pos) {
        return new AxisAlignedBB(0.125, 0, 0.125, 0.875, 1, 0.875);
    }
    @Nullable
    @Override
    public TileEntity createTileEntity(@NotNull World world, @NotNull IBlockState state) {
        return new TileEnergyTimeManipulator();
    }
}