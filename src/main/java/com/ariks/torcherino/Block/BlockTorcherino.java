package com.ariks.torcherino.Block;

import com.ariks.torcherino.util.Config;
import com.ariks.torcherino.Register.RegistryArray;
import com.ariks.torcherino.Torcherino;
import com.ariks.torcherino.util.IHasModel;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class BlockTorcherino extends BlockTorch implements IHasModel {
    public BlockTorcherino(String name) {
        this.setLightLevel(1);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setSoundType(SoundType.WOOD);
        this.setCreativeTab(Torcherino.torcherinoTab);
        RegistryArray.BLOCKS.add(this);
        RegistryArray.ITEMS.add(new ItemBlock(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
    }
    @Override
    public boolean onBlockActivated(@NotNull World worldIn, @NotNull BlockPos pos, @NotNull IBlockState state, @NotNull EntityPlayer playerIn, @NotNull EnumHand hand, @NotNull EnumFacing facing, float hitX, float hitY, float hitZ) {
        return Torcherino.proxy.openGui(worldIn, pos, playerIn);
    }
    @Override
    public void onBlockAdded(@NotNull World world, @NotNull BlockPos pos, @NotNull IBlockState state) {
        super.onBlockAdded(world, pos, state);
        if (Config.logPlacement) {
            Torcherino.logger.info(this.getClass().getName().substring(30) + "Torcherino place: " + pos.getX() + "," + pos.getY() + "," + pos.getZ());
        }
    }
    @Override
    public boolean hasTileEntity(@NotNull IBlockState state) {
        return true;
    }
    @Nullable
    @Override
    public TileEntity createTileEntity(@NotNull World world, @NotNull IBlockState state) {
        return null;
    }
    @Override
    public void registerModels() {
        Torcherino.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}


