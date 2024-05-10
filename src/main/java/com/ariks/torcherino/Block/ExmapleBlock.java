package com.ariks.torcherino.Block;

import com.ariks.torcherino.Torcherino;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
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
    public boolean hasTileEntity(@NotNull IBlockState state) {return true;}
    @Override
    public boolean canHarvestBlock(@NotNull IBlockAccess world, @NotNull BlockPos pos, @NotNull EntityPlayer player) {return true;}
}