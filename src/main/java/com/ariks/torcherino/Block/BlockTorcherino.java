package com.ariks.torcherino.Block;

import com.ariks.torcherino.Register.RegistryArray;
import com.ariks.torcherino.Tiles.TileCompresedTorch;
import com.ariks.torcherino.Tiles.TileDCompresedTorch;
import com.ariks.torcherino.Tiles.TileTorch;
import com.ariks.torcherino.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class BlockTorcherino extends ExmapleBlock implements IHasModel {
    private static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0.312, 0, 0.312, 0.688, 0.812, 0.688);
    public BlockTorcherino(String name) {
        super(name);
        this.setLightLevel(1);
    }
    @Override
    public @NotNull AxisAlignedBB getBoundingBox(@NotNull IBlockState state, @NotNull IBlockAccess source, @NotNull BlockPos pos) {
            return BOUNDING_BOX;
    }
    @Override
    public TileEntity createTileEntity(@NotNull World world, @NotNull IBlockState state) {
        Block block = state.getBlock();
        if (block == RegistryArray.Torch_lvl_1) {
            return new TileTorch.TileBase1();
        }
        if (block == RegistryArray.Torch_lvl_2) {
            return new TileTorch.TileBase2();
        }
        if (block == RegistryArray.Torch_lvl_3) {
            return new TileTorch.TileBase3();
        }
        if (block == RegistryArray.Torch_lvl_4) {
            return new TileTorch.TileBase4();
        }
        if (block == RegistryArray.Torch_lvl_5) {
            return new TileTorch.TileBase5();
        }
        if (block == RegistryArray.Compressed_Torch_lvl1) {
            return new TileCompresedTorch.CompressedTileBase1();
        }
        if (block == RegistryArray.Compressed_Torch_lvl2) {
            return new TileCompresedTorch.CompressedTileBase2();
        }
        if (block == RegistryArray.Compressed_Torch_lvl3) {
            return new TileCompresedTorch.CompressedTileBase3();
        }
        if (block == RegistryArray.Compressed_Torch_lvl4) {
            return new TileCompresedTorch.CompressedTileBase4();
        }
        if (block == RegistryArray.Compressed_Torch_lvl5) {
            return new TileCompresedTorch.CompressedTileBase5();
        }
        if (block == RegistryArray.D_Compressed_Torch_lvl1) {
            return new TileDCompresedTorch.DCompressedTileBase1();
        }
        if (block == RegistryArray.D_Compressed_Torch_lvl2) {
            return new TileDCompresedTorch.DCompressedTileBase2();
        }
        if (block == RegistryArray.D_Compressed_Torch_lvl3) {
            return new TileDCompresedTorch.DCompressedTileBase3();
        }
        if (block == RegistryArray.D_Compressed_Torch_lvl4) {
            return new TileDCompresedTorch.DCompressedTileBase4();
        }
        if (block == RegistryArray.D_Compressed_Torch_lvl5) {
            return new TileDCompresedTorch.DCompressedTileBase5();
        }
        return null;
    }
}