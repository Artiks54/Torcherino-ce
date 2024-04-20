package com.ariks.torcherino.Block;

import com.ariks.torcherino.Register.RegistryArray;
import com.ariks.torcherino.Torcherino;
import com.ariks.torcherino.util.Config;
import com.ariks.torcherino.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import java.util.Objects;

public abstract class ExmapleBlock extends Block implements IHasModel {
    public ExmapleBlock(String name) {
        super(Material.IRON);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(Torcherino.torcherinoTab);
        this.setHardness(1.2F);
        RegistryArray.BLOCKS.add(this);
        RegistryArray.ITEMS.add(new ItemBlock(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
    }
    @Override
    public boolean onBlockActivated(@NotNull World worldIn, @NotNull BlockPos pos, @NotNull IBlockState state, @NotNull EntityPlayer playerIn, @NotNull EnumHand hand, @NotNull EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (playerIn.getHeldItem(hand).getItem() == RegistryArray.time_storage_lvl1) {
            return false;
        }
        if (playerIn.getHeldItem(hand).getItem() == RegistryArray.time_storage_lvl2) {
            return false;
        }
        if (playerIn.getHeldItem(hand).getItem() == RegistryArray.time_storage_lvl3) {
            return false;
        }
        if (playerIn.getHeldItem(hand).getItem() == RegistryArray.time_storage_lvl4) {
            return false;
        }
        if (playerIn.getHeldItem(hand).getItem() == RegistryArray.time_storage_lvl5) {
            return false;
        }
        if (playerIn.getHeldItem(hand).getItem() == RegistryArray.time_storage_infinite) {
            return false;
        }
        return Torcherino.proxy.openGui(worldIn, pos, playerIn);
    }
    @Override
    public void onBlockAdded(@NotNull World world, @NotNull BlockPos pos, @NotNull IBlockState state) {
        super.onBlockAdded(world, pos, state);
        if (Config.DebugMod) {
            Torcherino.logger.info(this.getClass().getName().substring(30) + "Block place: "+getLocalizedName()+" Cord: " + pos.getX() + "," + pos.getY() + "," + pos.getZ()+" Dism: "+world.provider.getDimension());
        }
    }
    @Override
    public boolean isFullCube(@NotNull IBlockState state) {
        return false;
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
    public boolean hasTileEntity(@NotNull IBlockState state) {
        return true;
    }
    @Override
    public boolean canHarvestBlock(@NotNull IBlockAccess world, @NotNull BlockPos pos, @NotNull EntityPlayer player) {
        return true;
    }
    @Override
    public void registerModels() {
        Torcherino.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
