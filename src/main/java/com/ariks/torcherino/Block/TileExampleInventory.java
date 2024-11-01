package com.ariks.torcherino.Block;

import com.ariks.torcherino.util.InvWrapperRestricted;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.items.CapabilityItemHandler;
import org.jetbrains.annotations.NotNull;
import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class TileExampleInventory extends TileExampleContainer implements IInventory, ISidedInventory {
    public NonNullList<ItemStack> inventory;
    public InvWrapperRestricted invHandler;
    public TileExampleInventory(int InventorySize) {
        super();
        inventory = NonNullList.withSize(InventorySize, ItemStack.EMPTY);
        invHandler = new InvWrapperRestricted(this);
    }
    public void setSlotsForExtract(int slot) {
        this.setSlotsForExtract(Collections.singletonList(slot));
    }
    public void setSlotsForInsert(int slot) {
        this.setSlotsForInsert(Collections.singletonList(slot));
    }
    protected void setSlotsForExtract(List<Integer> slots) {
        invHandler.setSlotsExtract(slots);
    }
    protected void setSlotsForExtract(int startInclusive, int endInclusive) {
        setSlotsForExtract(IntStream.rangeClosed(startInclusive, endInclusive).boxed().collect(Collectors.toList()));
    }
    protected void setSlotsForInsert(int startInclusive, int endInclusive) {
        setSlotsForInsert(IntStream.rangeClosed(startInclusive, endInclusive).boxed().collect(Collectors.toList()));
    }
    protected void setSlotsForInsert(List<Integer> slots) {
        invHandler.setSlotsInsert(slots);
    }
    @Override
    public int getValue(int id) {return 0;}
    @Override
    public void setValue(int id, int value) {}
    @Override
    public Container createContainer(InventoryPlayer inventoryPlayer, EntityPlayer entityPlayer) {return null;}
    @Override
    public String getGuiID() {return null;}
    @Override
    public int getSizeInventory() {
        return inventory.size();
    }
    @Override
    public boolean isEmpty() {
        for (ItemStack stack : this.inventory) {
            if (!stack.isEmpty()) return false;
        }
        return true;
    }
    @Override
    public @NotNull ItemStack getStackInSlot(int index) {
        return this.inventory.get(index);
    }
    @Override
    public @NotNull ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(this.inventory, index, count);
    }
    @Override
    public @NotNull ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.inventory, index);
    }
    @Override
    public void setInventorySlotContents(int index, @NotNull ItemStack stack) {
        inventory.set(index, stack);
        if (stack.getCount() > getInventoryStackLimit()) {
            stack.setCount(getInventoryStackLimit());
        }
        markDirty();
    }
    @Override
    public int getInventoryStackLimit() {return 64;}
    @Override
    public void openInventory(@Nonnull EntityPlayer player) {}
    @Override
    public void closeInventory(@Nonnull EntityPlayer player) {}
    @Override
    public boolean isItemValidForSlot(int index, ItemStack itemStack) {return false;}
    @Override
    public int getField(int i) {return 0;}
    @Override
    public void setField(int i, int i1) {}
    @Override
    public int getFieldCount() {return 0;}
    @Override
    public void clear() {
        this.inventory.clear();
    }
    @Override
    public ITextComponent getDisplayName() {
        return null;
    }
    @Override
    public int[] getSlotsForFace(EnumFacing enumFacing) {
        return new int[0];
    }
    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return this.isItemValidForSlot(index, itemStackIn)
                && this.invHandler.canInsert(index);
    }
    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        return invHandler.canExtract(index);
    }
    @Override
    public @NotNull NBTTagCompound writeToNBT(@NotNull NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setTag("inventory", ItemStackHelper.saveAllItems(new NBTTagCompound(), inventory));
        return nbt;
    }
    @Override
    public void readFromNBT(@NotNull NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        NBTTagCompound inventoryTag = nbt.getCompoundTag("inventory");
        ItemStackHelper.loadAllItems(inventoryTag, inventory);
    }
    @Override
    public boolean hasCapability(net.minecraftforge.common.capabilities.Capability<?> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && this.getSizeInventory() > 0)
        {
            return true;
        }
        return super.hasCapability(capability, facing);
    }
    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, EnumFacing facing) {
        if (capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
        {
            return (T) invHandler;
        }
        return super.getCapability(capability, facing);
    }
}
