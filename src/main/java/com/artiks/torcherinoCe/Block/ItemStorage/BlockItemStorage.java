package com.artiks.torcherinoCe.Block.ItemStorage;

import com.artiks.torcherinoCe.Block.Core.BlockCustomModelTile;
import com.artiks.torcherinoCe.utility.NumberFormats;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockItemStorage extends BlockCustomModelTile {
    private final ItemStorageType type;
    public BlockItemStorage(String name, ItemStorageType type) {
        super(name);
        this.type = type;
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull AxisAlignedBB getBoundingBox(@NotNull IBlockState state, @NotNull IBlockAccess source, @NotNull BlockPos pos) {
        return new AxisAlignedBB(0.062, 0, 0.062, 0.938, 0.875, 0.938);
    }

    @Override
    public void addInformation(@NotNull ItemStack stack, @Nullable World world, @NotNull List<String> tooltip, @NotNull ITooltipFlag flag) {
        super.addInformation(stack, world, tooltip, flag);
        if (stack.hasTagCompound()) {
            NBTTagCompound nbt = stack.getTagCompound();
            if (nbt != null && nbt.hasKey("BlockEntityTag")) {
                NBTTagCompound blockEntityTag = nbt.getCompoundTag("BlockEntityTag");
                Map<String, Integer> itemCounts = new HashMap<>();
                if (blockEntityTag.hasKey("inventory")) {
                    NBTTagCompound inventoryTag = blockEntityTag.getCompoundTag("inventory");
                    NonNullList<ItemStack> inventory = NonNullList.withSize(4, ItemStack.EMPTY);
                    ItemStackHelper.loadAllItems(inventoryTag, inventory);
                    for (int i = 0; i < 3; i++) {
                        ItemStack itemStack = inventory.get(i);
                        if (!itemStack.isEmpty()) {
                            String itemName = itemStack.getDisplayName();
                            int count = itemStack.getCount();
                            itemCounts.put(itemName, itemCounts.getOrDefault(itemName, 0) + count);
                        }
                    }
                    ItemStack slot4Item = inventory.get(3);
                    if (!slot4Item.isEmpty()) {
                        String itemName = slot4Item.getDisplayName();
                        int count = slot4Item.getCount();
                        if (blockEntityTag.hasKey("storedItem") && blockEntityTag.hasKey("itemStored")) {
                            NBTTagCompound itemTag = blockEntityTag.getCompoundTag("storedItem");
                            ItemStack storedItem = new ItemStack(itemTag);
                            if (!storedItem.isEmpty() && storedItem.getDisplayName().equals(itemName)) {
                                int bufferCount = blockEntityTag.getInteger("itemStored");
                                count += bufferCount;
                            }
                        }
                        itemCounts.put(itemName, itemCounts.getOrDefault(itemName, 0) + count);
                    }
                }
                for (Map.Entry<String, Integer> entry : itemCounts.entrySet()) {
                    tooltip.add(entry.getKey() + " " + NumberFormats.formatNumber(entry.getValue()));
                }
            }
        }
    }

    @Override
    public boolean removedByPlayer(@NotNull IBlockState state, @NotNull World world, @NotNull BlockPos pos, @NotNull EntityPlayer player, boolean willHarvest) {
        if (player.capabilities.isCreativeMode) {
            TileEntity te = world.getTileEntity(pos);
            if (te instanceof TileItemStorage) {
                ((TileItemStorage) te).setDestroyedByCreativePlayer(true);
            }
        }
        return super.removedByPlayer(state, world, pos, player, willHarvest);
    }

    @Override
    public void harvestBlock(@NotNull World worldIn, @NotNull EntityPlayer player, @NotNull BlockPos pos, @NotNull IBlockState state, TileEntity te, @NotNull ItemStack stack) {
    }

    @Override
    public void breakBlock(@NotNull World worldIn, @NotNull BlockPos pos, @NotNull IBlockState state) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof TileItemStorage te) {
            if (!worldIn.isRemote && !te.isDestroyedByCreativePlayer()) {
                boolean hasItems = !te.getStoredItem().isEmpty() || te.getItemStored() > 0;
                for (int i = 0; i < te.getSizeInventory(); i++) {
                    if (!te.getStackInSlot(i).isEmpty()) {
                        hasItems = true;
                        break;
                    }
                }
                if (hasItems) {
                    ItemStack itemstack = new ItemStack(Item.getItemFromBlock(this));
                    NBTTagCompound nbt = new NBTTagCompound();
                    NBTTagCompound bet = new NBTTagCompound();
                    te.writeToNBT(bet);
                    nbt.setTag("BlockEntityTag", bet);
                    itemstack.setTagCompound(nbt);
                    InventoryHelper.spawnItemStack(worldIn, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, itemstack);
                } else {
                    InventoryHelper.spawnItemStack(worldIn, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, new ItemStack(Item.getItemFromBlock(this)));
                }
            }
        }
        super.breakBlock(worldIn, pos, state);
    }
    @Override
    public TileEntity createTileEntity(@NotNull World world, @NotNull IBlockState state) {
        return new TileItemStorage(type.getCapacity());
    }
}