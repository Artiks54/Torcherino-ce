package com.ariks.torcherinoCe.Block.Core;

import com.ariks.torcherinoCe.torcherinoCe;
import com.ariks.torcherinoCe.utility.Config;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class ExampleBlock extends Block {
    public ExampleBlock(String name, Material material) {
        super(material);
        this.setRegistryName(name);
        this.setTranslationKey(name);
        this.setCreativeTab(torcherinoCe.torcherinoTab);
        this.setHardness(1.0F);
        this.setResistance(1.0F);
    }
    @Override
    public void onBlockPlacedBy(@NotNull World worldIn, @NotNull BlockPos pos, @NotNull IBlockState state, @NotNull EntityLivingBase placer, @NotNull ItemStack stack) {
        if (Config.DebugMod && !worldIn.isRemote) {
            torcherinoCe.logger.info("TorcherinoCE-log: Block place: {} Cord: {},{},{} DismID: {} PlayerName: {}", getLocalizedName(), pos.getX(), pos.getY(), pos.getZ(), worldIn.provider.getDimension(), placer.getName());
        }
    }
}
