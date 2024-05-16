package com.ariks.torcherino.Block;

import com.ariks.torcherino.Items.TimeStorage;
import com.ariks.torcherino.Torcherino;
import com.ariks.torcherino.util.Config;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public abstract class ExmapleBlock extends Block {
    public ExmapleBlock(String name) {
        super(Material.IRON);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(Torcherino.torcherinoTab);
        this.setHardness(2.5F);
        this.setResistance(4.5f);
        this.setSoundType(SoundType.METAL);
        this.setHarvestLevel("pickaxe", 2);
    }
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(playerIn.getHeldItem(hand).getItem() instanceof TimeStorage){
            return false;
        }
        TileEntity tile = worldIn.getTileEntity(pos);
        if(!worldIn.isRemote && tile instanceof TileExampleContainer){
            int id = Integer.parseInt(((TileExampleContainer) tile).getGuiID());
            playerIn.openGui(Torcherino.instance,id,worldIn,pos.getX(),pos.getY(),pos.getZ());
        }
        return true;
    }
    @Override
    public void onBlockPlacedBy(@NotNull World worldIn, @NotNull BlockPos pos, @NotNull IBlockState state, @NotNull EntityLivingBase placer, @NotNull ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        if (Config.DebugMod && !worldIn.isRemote) {
            Torcherino.logger.info(
                    "TorcherinoCE-log: Block place: "+getUnlocalizedName()+" Cord: " + pos.getX() + "," + pos.getY() + "," + pos.getZ()+" DismID: "+worldIn.provider.getDimension()+" PlayerName: "+placer.getName());
        }
    }
    public boolean isOpaqueCube(@NotNull IBlockState state) {return false;}
    @Override
    public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {return false;}
    @Override
    public boolean isFullCube(IBlockState state) {return false;}
    @Override
    public boolean hasTileEntity(@NotNull IBlockState state) {return true;}
    @Override
    public boolean canHarvestBlock(@NotNull IBlockAccess world, @NotNull BlockPos pos, @NotNull EntityPlayer player) {return true;}
}