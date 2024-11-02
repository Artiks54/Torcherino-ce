package com.ariks.torcherino.Block.Core;

import com.ariks.torcherino.Torcherino;
import com.ariks.torcherino.util.Config;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class ExampleBlock extends Block {
    public ExampleBlock(String name, Material material) {
        super(material);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(Torcherino.torcherinoTab);
        this.setHardness(1.0F);
        this.setResistance(1.0F);
    }
    @Override
    public void onBlockPlacedBy(@NotNull World worldIn, @NotNull BlockPos pos, @NotNull IBlockState state, @NotNull EntityLivingBase placer, @NotNull ItemStack stack) {
        if (Config.DebugMod && !worldIn.isRemote) {
            Torcherino.logger.info(
                    "TorcherinoCE-log: Block place: "+getLocalizedName()+" Cord: " + pos.getX() + "," + pos.getY() + "," + pos.getZ()+" DismID: "+worldIn.provider.getDimension()+" PlayerName: "+placer.getName());
        }
    }
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        return false;
    }
}
