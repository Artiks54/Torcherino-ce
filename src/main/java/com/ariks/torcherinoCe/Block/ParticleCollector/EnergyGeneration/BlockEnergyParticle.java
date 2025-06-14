package com.ariks.torcherinoCe.Block.ParticleCollector.EnergyGeneration;

import com.ariks.torcherinoCe.Block.Core.BlockCustomModelTile;
import net.minecraft.block.state.IBlockState;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlockEnergyParticle extends BlockCustomModelTile {

    public BlockEnergyParticle(String name) {
        super(name);
    }
    @Override
    public @NotNull AxisAlignedBB getBoundingBox(@NotNull IBlockState state, @NotNull IBlockAccess source, @NotNull BlockPos pos) {
        return new AxisAlignedBB(0.062, 0, 0.062, 0.938, 0.875, 0.938);
    }
    @Override
    public void breakBlock(World worldIn, @NotNull BlockPos pos, @NotNull IBlockState state) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        assert tileEntity != null;
        InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory) tileEntity);
        super.breakBlock(worldIn, pos, state);
    }
    @Override
    public @Nullable TileEntity createTileEntity(@NotNull World world, @NotNull IBlockState state) {
        return new TileEnergyParticle();
    }
}
