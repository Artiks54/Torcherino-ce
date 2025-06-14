package com.ariks.torcherinoCe.Items;

import com.ariks.torcherinoCe.Block.Time.TileTime;
import com.ariks.torcherinoCe.Register.RegistryItems;
import com.ariks.torcherinoCe.utility.LocalizedStringKey;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class TimeStorage extends ItemBase {
    private final EnumStorage enumStorage;
    protected int MaxConfigStorageTimeItem() {
        return enumStorage.getStorage();
    }
    public TimeStorage(String name, EnumStorage enumStorage) {
        super(name);
        this.enumStorage = enumStorage;
        this.setMaxStackSize(1);
    }
    @Override
    public void getSubItems(@NotNull CreativeTabs tab, @NotNull NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            ItemStack emptyStack = new ItemStack(this);
            emptyStack.setTagCompound(new NBTTagCompound());
            assert emptyStack.getTagCompound() != null;
            emptyStack.getTagCompound().setInteger("Time", 0);
            items.add(emptyStack);
            ItemStack fullStack = new ItemStack(this);
            fullStack.setTagCompound(new NBTTagCompound());
            assert fullStack.getTagCompound() != null;
            fullStack.getTagCompound().setInteger("Time", MaxConfigStorageTimeItem());
            items.add(fullStack);
        }
    }
    @Override
    public void onUpdate(@NotNull ItemStack stack, @NotNull World worldIn, @NotNull Entity entityIn, int itemSlot, boolean isSelected) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
            assert stack.getTagCompound() != null;
            stack.getTagCompound().setInteger("Time", 0);
        }
    }
    @Override
    public boolean showDurabilityBar(@NotNull ItemStack stack) {
        return true;
    }
    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        if (stack.hasTagCompound()) {
            assert stack.getTagCompound() != null;
            if (stack.getTagCompound().hasKey("Time")) {
                int currentTime = stack.getTagCompound().getInteger("Time");
                int maxStorageTime = MaxConfigStorageTimeItem();
                return 1.0 - ((double) currentTime / (double) maxStorageTime);
            }
        }
        return 0;
    }
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, @NotNull List<String> tooltip, @NotNull ITooltipFlag flagIn) {
        LocalizedStringKey LS = new LocalizedStringKey();
        tooltip.add(TextFormatting.GRAY + LS.Str_Time_Storage_Tooltip);
        if (stack.hasTagCompound()) {
            assert stack.getTagCompound() != null;
            int time = stack.getTagCompound().getInteger("Time");
            if (stack.getItem() == RegistryItems.time_storage_infinite) {
                tooltip.add(TextFormatting.GRAY + LS.TimeCollected + " " + time);
            }
            if (stack.getItem() != RegistryItems.time_storage_infinite) {
                tooltip.add(TextFormatting.GRAY + LS.TimeCollected + " " + time + "/" + MaxConfigStorageTimeItem());
            }
        }
    }
    @Override
    public @NotNull EnumActionResult onItemUse(@NotNull EntityPlayer player, World worldIn, @NotNull BlockPos pos, @NotNull EnumHand hand, @NotNull EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof TileTime tile) {
                ItemStack heldItem = player.getHeldItem(hand);
                if (heldItem.hasTagCompound()) {
                    NBTTagCompound tagCompound = heldItem.getTagCompound();
                    assert tagCompound != null;
                    int storedTime = tagCompound.getInteger("Time");
                    int freeSpace = tile.energyTime.getMaxTimeStored() - tile.energyTime.getTimeStored();
                    if (!player.isSneaking()) {
                        if (freeSpace > 0) {
                            int transferTime = Math.min(storedTime, freeSpace);
                            tagCompound.setInteger("Time", storedTime - transferTime);
                            tile.energyTime.producedTime(transferTime);
                        }
                    } else {
                        int transferTime = Math.min(tile.energyTime.getTimeStored(), MaxConfigStorageTimeItem() - storedTime);
                        tagCompound.setInteger("Time", storedTime + transferTime);
                        tile.energyTime.consumeTime(transferTime);
                    }
                }
            }
        }
        return EnumActionResult.SUCCESS;
    }
}