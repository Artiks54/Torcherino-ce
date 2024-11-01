package com.ariks.torcherino.Block.Other;

import com.ariks.torcherino.Block.ExampleBlock;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import org.jetbrains.annotations.NotNull;
import java.util.Random;

public class BlockGrowAccelerator extends ExampleBlock {
    private final int Speed;
    public BlockGrowAccelerator(String name, int Speed) {
        super(name, Material.GRASS);
        this.Speed = Speed;
        this.setHardness(0.5F);
        this.setResistance(0.5F);
        this.setHarvestLevel("shovel",1);
        this.setSoundType(SoundType.GROUND);
        this.setTickRandomly(false);
    }
    @Override
    public void onBlockAdded(World worldIn, @NotNull BlockPos pos, @NotNull IBlockState state) {
        worldIn.scheduleBlockUpdate(pos, this, Speed, 1);
        super.onBlockAdded(worldIn, pos, state);
    }
    @Override
    public void updateTick(World worldIn, @NotNull BlockPos pos, @NotNull IBlockState state, @NotNull Random rand) {
        if(!worldIn.isRemote){
            BlockPos posUp = pos.up(2);
            IBlockState cropState = worldIn.getBlockState(posUp);
            Block cropStateBlock = cropState.getBlock();
            if (cropStateBlock instanceof IGrowable || cropStateBlock instanceof IPlantable) {
                cropStateBlock.updateTick(worldIn, posUp, cropState, worldIn.rand);
            }
            worldIn.scheduleBlockUpdate(pos, this, Speed, 1);
        }
    }
}