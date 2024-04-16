package com.ariks.torcherino.Block;

import com.ariks.torcherino.Register.RegistryArray;
import com.ariks.torcherino.Tiles.TileCollector;
import com.ariks.torcherino.Torcherino;
import com.ariks.torcherino.util.Config;
import com.ariks.torcherino.util.IHasModel;
import com.ariks.torcherino.util.LocalizedStringKey;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import java.util.Objects;

public class BlockCollector extends Block implements IHasModel {
    private static final AxisAlignedBB CUBE = new AxisAlignedBB(0.062, 0, 0.062, 0.938, 0.875, 0.938);
    public BlockCollector(String name) {
        super(Material.IRON);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(Torcherino.torcherinoTab);
        this.setHardness(0.8F);
        RegistryArray.BLOCKS.add(this);
        RegistryArray.ITEMS.add(new ItemBlock(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
    }
    @Override
    public boolean onBlockActivated(@NotNull World worldIn, @NotNull BlockPos pos, @NotNull IBlockState state, @NotNull EntityPlayer playerIn, @NotNull EnumHand hand, @NotNull EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (playerIn.getHeldItem(hand).getItem() == RegistryArray.Time_Storage) {
            return false;
        }
        return Torcherino.proxy.openGui(worldIn, pos, playerIn);
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
    public void onBlockAdded(@NotNull World world, @NotNull BlockPos pos, @NotNull IBlockState state) {
        super.onBlockAdded(world, pos, state);
        if (Config.logPlacement) {
            Torcherino.logger.info(this.getClass().getName().substring(30) + "Collector place: " + pos.getX() + "," + pos.getY() + "," + pos.getZ());
        }
    }
    @Nullable
    @Override
    public TileEntity createTileEntity(@NotNull World world, @NotNull IBlockState state) {
        return new TileCollector();
    }
    @Override
    public @NotNull AxisAlignedBB getBoundingBox(@NotNull IBlockState state, @NotNull IBlockAccess source, @NotNull BlockPos pos) {
        return CUBE;
    }
    @Override
    public boolean canHarvestBlock(@NotNull IBlockAccess world, @NotNull BlockPos pos, @NotNull EntityPlayer player) {
        return true;
    }
    @Override
    public void registerModels() {
        Torcherino.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(@NotNull ItemStack stack, @Nullable World player, List<String> tooltip, @NotNull ITooltipFlag advanced) {
        LocalizedStringKey LS = new LocalizedStringKey();
        tooltip.add(LS.StrCollectorInfoItem);
        super.addInformation(stack, player, tooltip, advanced);
    }
}
