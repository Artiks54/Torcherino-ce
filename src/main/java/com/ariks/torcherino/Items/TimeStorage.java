package com.ariks.torcherino.Items;

import com.ariks.torcherino.Tiles.TileCollector;
import com.ariks.torcherino.util.LocalizedStringKey;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class TimeStorage extends itemBase {

    LocalizedStringKey LS = new LocalizedStringKey();
    public TimeStorage(String name) {
        super(name);
        this.setMaxStackSize(1);
        this.setNoRepair();
    }
    @Override
    public void onUpdate(ItemStack stack, @NotNull World worldIn, @NotNull Entity entityIn, int itemSlot, boolean isSelected) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
            assert stack.getTagCompound() != null;
            stack.getTagCompound().setInteger("Time", 0);
        }
    }
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, @NotNull List<String> tooltip, @NotNull ITooltipFlag flagIn) {
        if (stack.hasTagCompound()) {
            assert stack.getTagCompound() != null;
            int time = stack.getTagCompound().getInteger("Time");
            tooltip.add(TextFormatting.GRAY + LS.TimeCollector +": " +TextFormatting.GREEN+ time);
        }
        tooltip.add(TextFormatting.GRAY + LS.Str_Time_Storage_Tooltip);
    }
    @Override
    public @NotNull EnumActionResult onItemUse(@NotNull EntityPlayer player, World worldIn, @NotNull BlockPos pos, @NotNull EnumHand hand, @NotNull EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof TileCollector && player.getHeldItem(hand).hasTagCompound()) {
                TileCollector tileCollector = (TileCollector) tileEntity;
                NBTTagCompound tagCompound = player.getHeldItem(hand).getTagCompound();
                assert tagCompound != null;
                int storedTime = tagCompound.getInteger("Time");
                if (player.isSneaking()) {
                    int collectedTime = tileCollector.TimeCollect;
                    tagCompound.setInteger("Time", storedTime + collectedTime);
                    tileCollector.TimeCollect = 0;
                } else {
                    int collectedTime = tileCollector.TimeCollect;
                    tileCollector.TimeCollect = (storedTime + collectedTime);
                    tagCompound.setInteger("Time", 0);
                }
            }
        }
        return EnumActionResult.SUCCESS;
    }
}
