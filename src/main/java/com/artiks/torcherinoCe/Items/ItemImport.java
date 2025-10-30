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

public class ItemImport extends ItemBase {
    public ItemImport(String name) {
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
            nbt.setString("importSide", facing.getName());
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.PASS;
    }

    public static EnumFacing getImportSide(ItemStack stack) {
        if (stack.hasTagCompound()) {
            NBTTagCompound nbt = stack.getTagCompound();
            assert nbt != null;
            if (nbt.hasKey("importSide")) {
                String sideName = nbt.getString("importSide");
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
            if (nbt.hasKey("importSide")) {
                String side = nbt.getString("importSide");
                tooltip.add("Facing: " + side);
            }
        } else {
            tooltip.add(LocalizedStringKey.StrTooltipSides);
        }
    }
}