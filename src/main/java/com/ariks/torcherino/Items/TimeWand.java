package com.ariks.torcherino.Items;

import com.ariks.torcherino.Register.AccelerationRegistry;
import com.ariks.torcherino.Tiles.TileTorcherinoBase;
import com.ariks.torcherino.util.Config;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import java.util.Random;

public class TimeWand extends itemBase {
    public TimeWand(String name) {
        super(name);
        this.setMaxDamage(500);
        this.setMaxStackSize(1);
    }
    @Override
    public @NotNull EnumActionResult onItemUse(@NotNull EntityPlayer player, World worldIn, @NotNull BlockPos pos, @NotNull EnumHand hand, @NotNull EnumFacing facing, float hitX, float hitY, float hitZ) {
            if (!worldIn.isRemote) {
                ItemStack stack = player.getHeldItem(hand);
                IBlockState blockState = worldIn.getBlockState(pos);
                Block block = blockState.getBlock();
                TileEntity tile = worldIn.getTileEntity(pos);
                if (tile instanceof TileTorcherinoBase || AccelerationRegistry.isBlockBlacklisted(block)) {
                    return EnumActionResult.FAIL;
                }
                if (!(block.getTickRandomly() || (tile instanceof ITickable && block.hasTileEntity(blockState)))) {
                    return EnumActionResult.FAIL;
                }
                for (int i = 0; i < Config.SpeedWand; i++) {
                    block.updateTick(worldIn, pos, blockState, new Random());
                    if (tile instanceof ITickable) {
                        ((ITickable) tile).update();
                    }
                    if (tile != null && tile.isInvalid()) {
                        break;
                    }
                }
                if(Config.BooleanParcWand){
                    ((WorldServer) worldIn).spawnParticle(EnumParticleTypes.FLAME, pos.getX() + 0.5, pos.getY() + 1.5, pos.getZ() + 0.5, 1, 0.15, 0.15, 0.15, 0.02);
                }
                stack.damageItem(1,player);
            }
        return EnumActionResult.SUCCESS;
    }
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, @NotNull ITooltipFlag flagIn) {
        int durability = stack.getMaxDamage() - stack.getItemDamage();
        tooltip.add(TextFormatting.GOLD + "Durability: "+" " + durability + "/" + stack.getMaxDamage());
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
    @Override
    public boolean showDurabilityBar(@NotNull ItemStack stack) {
        return stack.isItemDamaged();
    }
}