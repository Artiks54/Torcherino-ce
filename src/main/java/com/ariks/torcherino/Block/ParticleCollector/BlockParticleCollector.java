package com.ariks.torcherino.Block.ParticleCollector;

import com.ariks.torcherino.Block.ExampleBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class BlockParticleCollector extends ExampleBlock {
    public BlockParticleCollector(String name) {
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
    public TileEntity createTileEntity(@NotNull World world, @NotNull IBlockState state) {
        return new TileParticleCollector();
    }
    @Override
    public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        if (!world.isRemote) {
            TileEntity tile =  world.getTileEntity(pos);
            if (tile != null) {
                if (tile instanceof TileParticleCollector) {
                    ItemStack itemStack = new ItemStack(this.getItemDropped(state, world.rand, 0));
                    if (!itemStack.isEmpty()) {
                        NBTTagCompound nbt = new NBTTagCompound();
                        nbt.setInteger("Level",((TileParticleCollector) tile).level);
                        if(((TileParticleCollector) tile).level < 5){
                            nbt.setInteger("TotalGeneratedUp",((TileParticleCollector) tile).TotalGeneratedUp);
                        }
                        itemStack.setTagCompound(nbt);
                        spawnAsEntity(world, pos, itemStack);
                    }
                }
            }
        }
        super.onBlockHarvested(world, pos, state, player);
    }
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        if (!worldIn.isRemote) {
            TileEntity tile =  worldIn.getTileEntity(pos);
            if (tile != null && stack.hasTagCompound()) {
                if (tile instanceof TileParticleCollector) {
                    NBTTagCompound nbt = stack.getTagCompound();
                    assert nbt != null;
                    if (nbt.hasKey("Level")) {
                        ((TileParticleCollector) tile).level = nbt.getInteger("Level");
                    }
                    if (nbt.hasKey("Level")) {
                        ((TileParticleCollector) tile).TotalGeneratedUp = nbt.getInteger("TotalGeneratedUp");
                    }
                }
            }
        }
    }
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (stack.hasTagCompound()) {
            NBTTagCompound nbt = stack.getTagCompound();
            assert nbt != null;
            if (nbt.hasKey("Level")) {
                int Level = nbt.getInteger("Level");
                tooltip.add(TextFormatting.GRAY + "Level: " + Level);
            }
            if (nbt.hasKey("TotalGeneratedUp")) {
                int TotalGeneratedUp = nbt.getInteger("TotalGeneratedUp");
                tooltip.add(TextFormatting.GRAY + "Generated: " + TotalGeneratedUp);
            }
        }
    }
}