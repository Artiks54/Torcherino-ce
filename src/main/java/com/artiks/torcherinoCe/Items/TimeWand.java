package com.artiks.torcherinoCe.Items;

import com.artiks.torcherinoCe.Block.Energy.Torcherino.RegistryAcceleration;
import com.artiks.torcherinoCe.Register.RegistryItems;
import com.artiks.torcherinoCe.utility.Config;
import com.artiks.torcherinoCe.utility.LocalizedStringKey;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import java.util.Random;

public class TimeWand extends ItemBase {
    public final EnumWands enumWands;
    protected int SpeedWand(){
        return enumWands.getSpeed();
    }
    public TimeWand(String name,EnumWands enumWands,int durability) {
        super(name);
        this.enumWands = enumWands;
        this.setMaxDamage(durability);
        this.setMaxStackSize(1);
    }
    @Override
    public @NotNull EnumActionResult onItemUse(@NotNull EntityPlayer player, World worldIn, @NotNull BlockPos pos, @NotNull EnumHand hand, @NotNull EnumFacing facing, float hitX, float hitY, float hitZ) {
            if (!worldIn.isRemote) {
                ItemStack stack = player.getHeldItem(hand);
                IBlockState blockState = worldIn.getBlockState(pos);
                Block block = blockState.getBlock();
                TileEntity tile = worldIn.getTileEntity(pos);
                if (RegistryAcceleration.isBlockBlacklisted(blockState)) {
                    return EnumActionResult.FAIL;
                }
                if (!(block.getTickRandomly() || (tile instanceof ITickable && block.hasTileEntity(blockState)))) {
                    return EnumActionResult.FAIL;
                }
                for (int i = 0; i < SpeedWand() * 100; i++) {
                    block.updateTick(worldIn, pos, blockState, new Random());
                    if (tile instanceof ITickable) {
                        ((ITickable) tile).update();
                    }
                    if (tile != null && tile.isInvalid()) {
                        break;
                    }
                }
                if (Config.BooleanParcWand) {
                    ((WorldServer) worldIn).spawnParticle(EnumParticleTypes.FLAME, pos.getX() + 0.5, pos.getY() + 1.5, pos.getZ() + 0.5, 1, 0.15, 0.15, 0.15, 0.02);
                }
                if (stack.getItem() != RegistryItems.time_Wand_infinite) {
                    stack.damageItem(1, player);
                }
            }
        return EnumActionResult.SUCCESS;
    }
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, @NotNull ITooltipFlag flagIn) {
            int durability = stack.getMaxDamage() - stack.getItemDamage();
            tooltip.add(TextFormatting.GRAY + LocalizedStringKey.Str_Time_Wand_Tooltip);
            if (stack.getItem() != RegistryItems.time_Wand_infinite) {
            tooltip.add(TextFormatting.GRAY + LocalizedStringKey.StrWandInfoItem + " " + durability);
            }
            tooltip.add(TextFormatting.GRAY + LocalizedStringKey.StrTextSpeed +": "+enumWands.getSpeed() * 100 + "%");
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
    @Override
    public boolean showDurabilityBar(@NotNull ItemStack stack) {
        if (stack.getItem() != RegistryItems.time_Wand_infinite) {
            return stack.isItemDamaged();
        }
        return false;
    }
}