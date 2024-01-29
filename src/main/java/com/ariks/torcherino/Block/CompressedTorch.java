package com.ariks.torcherino.Block;
import com.ariks.torcherino.Block.Base.BlockBase;
import com.ariks.torcherino.Register.RegistryArray;
import com.ariks.torcherino.Tiles.TileCompresedTorch.*;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CompressedTorch extends BlockBase {
    public CompressedTorch(String name) {
        super(name);
    }
    @Nullable
    @Override
    public TileEntity createTileEntity(@NotNull World world, IBlockState state) {
        Block block = state.getBlock();
        if (block == RegistryArray.Compressed_Torch_lvl1) {
            return new CompressedTileBase1();
        }
        if (block == RegistryArray.Compressed_Torch_lvl2) {
            return new CompressedTileBase2();
        }
        if (block == RegistryArray.Compressed_Torch_lvl3) {
            return new CompressedTileBase3();
        }
        if (block == RegistryArray.Compressed_Torch_lvl4) {
            return new CompressedTileBase4();
        }
        if (block == RegistryArray.Compressed_Torch_lvl5) {
            return new CompressedTileBase5();
        }
        return null;
    }
}