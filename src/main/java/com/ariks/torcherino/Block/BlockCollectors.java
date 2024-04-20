package com.ariks.torcherino.Block;

import com.ariks.torcherino.Tiles.TileCollectors;
import com.ariks.torcherino.util.LocalizedStringKey;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class BlockCollectors extends ExmapleBlock{
    private static final AxisAlignedBB CUBE = new AxisAlignedBB(0.062, 0, 0.062, 0.938, 0.875, 0.938);
    public BlockCollectors(String name) {
        super(name);
    }
    @Nullable
    @Override
    public TileEntity createTileEntity(@NotNull World world, @NotNull IBlockState state) {
        return new TileCollectors();
    }
    @Override
    public @NotNull AxisAlignedBB getBoundingBox(@NotNull IBlockState state, @NotNull IBlockAccess source, @NotNull BlockPos pos) {
        return CUBE;
    }
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(@NotNull ItemStack stack, @Nullable World player, List<String> tooltip, @NotNull ITooltipFlag advanced) {
        LocalizedStringKey LS = new LocalizedStringKey();
        tooltip.add(LS.StrCollectorsInfoItem);
        super.addInformation(stack, player, tooltip, advanced);
    }
}
