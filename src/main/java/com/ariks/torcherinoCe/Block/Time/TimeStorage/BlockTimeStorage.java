package com.ariks.torcherinoCe.Block.Time.TimeStorage;

import com.ariks.torcherinoCe.Block.Time.TimeBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlockTimeStorage extends TimeBlock {
    public BlockTimeStorage(String name) {
        super(name);
    }
    @Nullable
    @Override
    public TileEntity createTileEntity(@NotNull World world, @NotNull IBlockState state) {
        return new TileTimeStorage();
    }
    @Override
    public @NotNull AxisAlignedBB getBoundingBox(@NotNull IBlockState state, @NotNull IBlockAccess source, @NotNull BlockPos pos) {
        return new AxisAlignedBB(0.062, 0, 0.062, 0.938, 0.875, 0.938);
    }
}