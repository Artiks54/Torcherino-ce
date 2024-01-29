package com.ariks.torcherino.Block;
import com.ariks.torcherino.Block.Base.BlockBase;
import com.ariks.torcherino.Register.RegistryArray;
import com.ariks.torcherino.Tiles.TileTorch;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlockTorchBase extends BlockBase  {
    public BlockTorchBase(String name) {
        super(name);
    }
    @Nullable
    @Override
    public TileEntity createTileEntity(@NotNull World world, IBlockState state) {
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
        return null;
    }
}
