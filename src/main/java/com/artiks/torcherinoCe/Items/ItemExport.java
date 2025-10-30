package com.artiks.torcherinoCe.Items;


import com.artiks.torcherinoCe.utility.LocalizedStringKey;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class ItemExport extends ItemBase {
    public ItemExport(String name) {
        super(name);
    }

    @Override
    public @NotNull EnumActionResult onItemUse(@NotNull EntityPlayer player, World world, @NotNull BlockPos pos, @NotNull EnumHand hand, @NotNull EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            ItemStack stack = player.getHeldItem(hand);
            if (!stack.hasTagCompound()) {
                stack.setTagCompound(new NBTTagCompound());
            }
            NBTTagCompound nbt = stack.getTagCompound();
            assert nbt != null;
            nbt.setString("exportSide", facing.getName());
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.PASS;
    }

    public static EnumFacing getExportSide(ItemStack stack) {
        if (stack.hasTagCompound()) {
            NBTTagCompound nbt = stack.getTagCompound();
            assert nbt != null;
            if (nbt.hasKey("exportSide")) {
                String sideName = nbt.getString("exportSide");
                return EnumFacing.byName(sideName);
            }
        }
        return null;
    }
    @Override
    public void addInformation(@NotNull ItemStack stack, World world, @NotNull List<String> tooltip, @NotNull ITooltipFlag flag) {
        super.addInformation(stack, world, tooltip, flag);
        if (stack.hasTagCompound()) {
            NBTTagCompound nbt = stack.getTagCompound();
            assert nbt != null;
            if (nbt.hasKey("exportSide")) {
                String side = nbt.getString("exportSide");
                tooltip.add("Facing: " + side);
            }
        } else {
            tooltip.add(LocalizedStringKey.StrTooltipSides);
        }
    }
}