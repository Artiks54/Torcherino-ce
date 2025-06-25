package com.ariks.torcherinoCe.Items;

import com.ariks.torcherinoCe.utility.LocalizedStringKey;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class itemMarker extends ItemBase {
    public itemMarker(String name) {
        super(name);
        this.setMaxStackSize(1);
    }

    @Override
    public @NotNull EnumActionResult onItemUse(@NotNull EntityPlayer player, World worldIn, @NotNull BlockPos pos, @NotNull EnumHand hand, @NotNull EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            ItemStack stack = player.getHeldItem(hand);
            Block block = worldIn.getBlockState(pos).getBlock();
            if (player.isSneaking()) {
                if (!stack.hasTagCompound()) {
                    stack.setTagCompound(new NBTTagCompound());
                }
                NBTTagCompound nbt = stack.getTagCompound();
                assert nbt != null;
                nbt.setInteger("x", pos.getX());
                nbt.setInteger("y", pos.getY());
                nbt.setInteger("z", pos.getZ());
                nbt.setString("name",block.getLocalizedName());

                player.sendMessage(new TextComponentString(
                        String.format("Cord save: %d, %d, %d",
                                pos.getX(), pos.getY(), pos.getZ())
                ));
            } else {
                if (stack.hasTagCompound()) {
                    stack.setTagCompound(null);
                    player.sendMessage(new TextComponentString("cord delete"));
                }
            }
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.PASS;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, @NotNull List<String> tooltip, @NotNull ITooltipFlag flagIn) {
        LocalizedStringKey LS = new LocalizedStringKey();
        tooltip.add(TextFormatting.GRAY + LS.Str_Gps_marker);
        if (stack.hasTagCompound()) {
            NBTTagCompound nbt = stack.getTagCompound();
            assert nbt != null;
            tooltip.add(TextFormatting.GRAY + "X: " + nbt.getInteger("x"));
            tooltip.add(TextFormatting.GRAY + "Y: " + nbt.getInteger("y"));
            tooltip.add(TextFormatting.GRAY + "Z: " + nbt.getInteger("z"));
            tooltip.add(TextFormatting.GRAY + "Name: " + nbt.getString("name"));
        } else {
            tooltip.add(TextFormatting.RED + "Not cord");
        }
    }
}