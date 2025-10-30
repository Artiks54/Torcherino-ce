package com.artiks.torcherinoCe.Block.Core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SuppressWarnings("NullableProblems")
public abstract class TileExampleInventory extends TileExampleContainer implements IInventory, ISidedInventory, ITickable {
    public NonNullList<ItemStack> inventory;
    public InvWrapperRestricted invHandler;
    private EnumFacing ImportSide = null;
    private EnumFacing ExportSide = null;

    public TileExampleInventory(int InventorySize) {
        super();
        inventory = NonNullList.withSize(InventorySize, ItemStack.EMPTY);
        invHandler = new InvWrapperRestricted(this);
    }


    @Override
    public void update() {
        if (world.isRemote) return;
        if (ExportSide != null) {
            autoExport();
        }
        if (ImportSide!= null) {
            autoInsert();
        }
    }

    public void autoExport() {
        BlockPos pos = getPos().offset(ExportSide);
        TileEntity tile = world.getTileEntity(pos);
        if (tile == null) return;
        IItemHandler targetHandler = getItemHandler(tile, ExportSide.getOpposite());
        if (targetHandler == null) return;
        int targetSlots = targetHandler.getSlots();
        List<Integer> outputSlots = invHandler.getSlotsExtract();
        for (int slotIndex : outputSlots) {
            ItemStack stackInSlot = getStackInSlot(slotIndex);
            if (stackInSlot.isEmpty()) continue;
            ItemStack toTransfer = stackInSlot.copy();
            ItemStack remaining = insertItem(targetHandler, toTransfer, true, targetSlots);
            if (remaining.isEmpty()) {
                setInventorySlotContents(slotIndex, ItemStack.EMPTY);
                insertItem(targetHandler, toTransfer, false, targetSlots);
            } else if (remaining.getCount() < toTransfer.getCount()) {
                int transferred = toTransfer.getCount() - remaining.getCount();
                stackInSlot.shrink(transferred);
                if (stackInSlot.isEmpty()) setInventorySlotContents(slotIndex, ItemStack.EMPTY);
                toTransfer.setCount(transferred);
                insertItem(targetHandler, toTransfer, false, targetSlots);
            }
        }
    }

    public void autoInsert() {
        BlockPos sourcePos = getPos().offset(ImportSide);
        TileEntity sourceTile = world.getTileEntity(sourcePos);
        if (sourceTile == null) return;
        IItemHandler sourceHandler = getItemHandler(sourceTile, ImportSide.getOpposite());
        if (sourceHandler == null) return;
        int sourceSlots = sourceHandler.getSlots();
        List<Integer> inputSlots = invHandler.getSlotsInsert();
        for (int slotIndex : inputSlots) {
            ItemStack currentStack = getStackInSlot(slotIndex);
            int spaceInSlot = 64 - currentStack.getCount();
            if (spaceInSlot <= 0) continue;
            boolean transferredAny = false;
            for (int j = 0; j < sourceSlots; j++) {
                ItemStack simulatedExtract = sourceHandler.extractItem(j, spaceInSlot, true);
                if (simulatedExtract.isEmpty()) continue;
                if (!isItemValidForSlot(slotIndex, simulatedExtract)) continue;
                if (currentStack.isEmpty() || ItemHandlerHelper.canItemStacksStack(currentStack, simulatedExtract)) {
                    ItemStack extracted = sourceHandler.extractItem(j, spaceInSlot, false);
                    if (!extracted.isEmpty()) {
                        if (currentStack.isEmpty()) {
                            setInventorySlotContents(slotIndex, extracted);
                        } else {
                            currentStack.grow(extracted.getCount());
                        }
                        spaceInSlot -= extracted.getCount();
                        transferredAny = true;
                        if (spaceInSlot <= 0) break;
                    }
                }
            }
            if (!transferredAny) {
                break;
            }
        }
    }

    public void setExportSide(EnumFacing exportSide) { this.ExportSide = exportSide; }

    public void setImportSide(EnumFacing importSide) { this.ImportSide = importSide; }


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

    @SuppressWarnings("SameParameterValue")
    protected void setSlotsForInsert(int startInclusive, int endInclusive) {
        setSlotsForInsert(IntStream.rangeClosed(startInclusive, endInclusive).boxed().collect(Collectors.toList()));
    }

    protected void setSlotsForInsert(List<Integer> slots) {
        invHandler.setSlotsInsert(slots);
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public @NotNull String getGuiID() {return null;}

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

    public void addInventoryItemOutput(ItemStack stackToAdd) {
        if (stackToAdd.isEmpty() || stackToAdd.getCount() <= 0) {
            return;
        }
        if (!hasEnoughOutputSpace(stackToAdd)) {
            return;
        }
        List<Integer> outputSlots = invHandler.getSlotsExtract();
        ItemStack remaining = stackToAdd.copy();
        for (int slotIndex : outputSlots) {
            if (remaining.isEmpty()) {
                break;
            }
            ItemStack currentStack = getStackInSlot(slotIndex);
            if (currentStack.isEmpty()) {
                int transferAmount = Math.min(remaining.getCount(), getInventoryStackLimit());
                ItemStack stackToSet = remaining.copy();
                stackToSet.setCount(transferAmount);
                setInventorySlotContents(slotIndex, stackToSet);
                remaining.shrink(transferAmount);
            } else if (ItemHandlerHelper.canItemStacksStack(currentStack, remaining)) {
                int spaceAvailable = getInventoryStackLimit() - currentStack.getCount();
                if (spaceAvailable > 0) {
                    int transferAmount = Math.min(remaining.getCount(), spaceAvailable);
                    currentStack.grow(transferAmount);
                    setInventorySlotContents(slotIndex, currentStack);
                    remaining.shrink(transferAmount);
                }
            }
        }
    }

    public boolean hasEnoughOutputSpace(ItemStack stackToAdd) {
        List<Integer> outputSlots = invHandler.getSlotsExtract();
        int remainingAmount = stackToAdd.getCount();
        for (int slotIndex : outputSlots) {
            if (remainingAmount <= 0) {
                break;
            }
            ItemStack currentStack = getStackInSlot(slotIndex);
            if (currentStack.isEmpty()) {
                remainingAmount -= Math.min(remainingAmount, getInventoryStackLimit());
            } else if (ItemHandlerHelper.canItemStacksStack(currentStack, stackToAdd)) {
                int spaceAvailable = getInventoryStackLimit() - currentStack.getCount();
                remainingAmount -= Math.min(remainingAmount, spaceAvailable);
            }
        }
        return remainingAmount <= 0;
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

    public boolean hasValidOutputSpace() {
        for (int slot : invHandler.getSlotsExtract()) {
            ItemStack stack = getStackInSlot(slot);
            if (stack.isEmpty() || stack.getCount() < getInventoryStackLimit()) {
                return true;
            }
        }
        return false;
    }

    public IItemHandler getItemHandler(@Nullable TileEntity tile, EnumFacing side) {
        if (tile == null) return null;
        IItemHandler handler = tile.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side) ?
                tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side) : null;
        if (handler == null) {
            if (side != null && tile instanceof ISidedInventory) {
                handler = new SidedInvWrapper((ISidedInventory) tile, side);
            } else if (tile instanceof IInventory) {
                handler = new InvWrapper((IInventory) tile);
            }
        }
        return handler;
    }

    @Nonnull
    public ItemStack insertItem(IItemHandler iItemHandler, @Nonnull ItemStack stack, boolean simulate, int slots) {
        if (iItemHandler == null || stack.isEmpty()) return stack;
        slots = Math.min(slots, iItemHandler.getSlots());
        for (int i = 0; i < slots; i++) {
            ItemStack remaining = iItemHandler.insertItem(i, stack, simulate);
            if (remaining.isEmpty()) return ItemStack.EMPTY;
            if (remaining.getCount() != stack.getCount()) {
                return remaining;
            }
        }
        return stack;
    }

    @Override
    public int getInventoryStackLimit() {return 64;}

    @Override
    public void openInventory(@Nonnull EntityPlayer player) {}

    @Override
    public void closeInventory(@Nonnull EntityPlayer player) {}

    @Override
    public boolean isItemValidForSlot(int index, @NotNull ItemStack itemStack) {return true;}

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
    public int @NotNull [] getSlotsForFace(@NotNull EnumFacing enumFacing) {
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int index, @NotNull ItemStack itemStackIn, @NotNull EnumFacing direction) {
        return this.isItemValidForSlot(index, itemStackIn)
                && this.invHandler.canInsert(index);
    }

    @Override
    public boolean canExtractItem(int index, @NotNull ItemStack stack, @NotNull EnumFacing direction) {
        return invHandler.canExtract(index);
    }

    @Override
    public @NotNull NBTTagCompound writeToNBT(@NotNull NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setTag("inventory", ItemStackHelper.saveAllItems(new NBTTagCompound(), inventory));
        if(ImportSide != null){
            nbt.setInteger("ImportSide",ImportSide.getIndex());
        }
        if(ExportSide != null) {
            nbt.setInteger("ExportSide", ExportSide.getIndex());
        }
        return nbt;
    }

    @Override
    public void readFromNBT(@NotNull NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        NBTTagCompound inventoryTag = nbt.getCompoundTag("inventory");
        ItemStackHelper.loadAllItems(inventoryTag, inventory);
        if(nbt.hasKey("ImportSide")){
            ImportSide = EnumFacing.byIndex(nbt.getInteger("ImportSide"));
        }
        if(nbt.hasKey("ExportSide")){
            ExportSide = EnumFacing.byIndex(nbt.getInteger("ExportSide"));
        }
    }

    @Override
    public boolean hasCapability(@NotNull Capability<?> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && this.getSizeInventory() > 0)
        {
            return true;
        }

        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(@NotNull Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
        {
            return (T) invHandler;
        }
        return super.getCapability(capability, facing);
    }
}
