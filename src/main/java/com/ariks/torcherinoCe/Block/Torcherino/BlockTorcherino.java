package com.ariks.torcherinoCe.Block.Torcherino;

import com.ariks.torcherinoCe.Block.Core.BlockCustomModelTile;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlockTorcherino extends BlockCustomModelTile {
    private final TorcherinoEnumLevel tileType;
    public BlockTorcherino(String name,TorcherinoEnumLevel tileType) {
        super(name);
        this.setLightLevel(1);
        this.tileType = tileType;
    }
    @Nullable
    @Override
    public TileEntity createTileEntity(@NotNull World world, @NotNull IBlockState state) {
        return new TileTorcherinoBase(tileType);
    }
    @Override
    public boolean canConnectRedstone(@NotNull IBlockState state, @NotNull IBlockAccess world, @NotNull BlockPos pos, @Nullable EnumFacing side) {
        return true;
    }
    @SuppressWarnings("deprecation")
    @Override
    public void neighborChanged(@NotNull IBlockState state, World worldIn, @NotNull BlockPos pos, @NotNull Block blockIn, @NotNull BlockPos fromPos) {
        if(!worldIn.isRemote){
            TileEntity tile = worldIn.getTileEntity(pos);
            if(tile instanceof TileTorcherinoBase) ((TileTorcherinoBase) tile).setRedstoneSignal(worldIn.isBlockPowered(pos));
        }
    }
    @SuppressWarnings("deprecation")
    @Override
    public @NotNull AxisAlignedBB getBoundingBox(@NotNull IBlockState state, @NotNull IBlockAccess source, @NotNull BlockPos pos) {
        return new AxisAlignedBB(0.312, 0, 0.312, 0.688, 0.812, 0.688);
    }
}