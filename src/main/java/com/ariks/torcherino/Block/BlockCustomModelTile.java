package com.ariks.torcherino.Block;

import com.ariks.torcherino.Torcherino;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class BlockCustomModelTile extends ExampleBlock {

    public BlockCustomModelTile(String name) {
        super(name,Material.IRON);
        this.setHardness(2.5F);
        this.setResistance(4.5f);
        this.setSoundType(SoundType.METAL);
        this.setHarvestLevel("pickaxe", 2);
    }
    @Override
    public boolean hasTileEntity(@NotNull IBlockState state) {
        return true;
    }

    @Override
    public boolean isOpaqueCube(@NotNull IBlockState state) {
        return false;
    }
    @Override
    public boolean isNormalCube(@NotNull IBlockState state, @NotNull IBlockAccess world, @NotNull BlockPos pos) {
        return false;
    }
    @Override
    public boolean isFullCube(@NotNull IBlockState state) {
        return false;
    }
    @Override
    public boolean onBlockActivated(World worldIn, @NotNull BlockPos pos, @NotNull IBlockState state, @NotNull EntityPlayer playerIn, @NotNull EnumHand hand, @NotNull EnumFacing facing, float hitX, float hitY, float hitZ) {
        TileEntity tile = worldIn.getTileEntity(pos);
        if(!worldIn.isRemote && tile instanceof TileExampleContainer){
            int id = Integer.parseInt(((TileExampleContainer) tile).getGuiID());
            playerIn.openGui(Torcherino.instance,id,worldIn,pos.getX(),pos.getY(),pos.getZ());
        }
        return true;
    }
}
