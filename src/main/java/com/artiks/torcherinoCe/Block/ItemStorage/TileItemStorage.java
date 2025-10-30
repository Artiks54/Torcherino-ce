package com.artiks.torcherinoCe.Block.ItemStorage;

import com.artiks.torcherinoCe.Block.Core.TileExampleInventory;
import com.artiks.torcherinoCe.Register.RegistryGui;
import com.artiks.torcherinoCe.network.RegistryNetwork;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import javax.annotation.Nonnull;
import java.util.Map;

public class TileItemStorage extends TileExampleInventory implements ITickable {

    private int itemCapacity;
    private int itemStored;
    private ItemStack storedItem = ItemStack.EMPTY;
    private ItemStack lastStoredItem = ItemStack.EMPTY;
    private boolean destroyedByCreativePlayer = false;
    protected NetworkRegistry.TargetPoint packetTargetPoint;
    public int getItemStored() {return itemStored;}
    public void setStoredItem(ItemStack storedItem) {
        this.storedItem = storedItem;
    }

    public ItemStack getStoredItem() {
        return storedItem;
    }

    public TileItemStorage(int ITEM_CAPACITY) {
        super(4);
        this.setSlotsForInsert(0);
        this.setSlotsForExtract(3);
        this.itemCapacity = ITEM_CAPACITY;
    }

    @SuppressWarnings("unused")
    public TileItemStorage(){
        super(4);
        this.setSlotsForInsert(0);
        this.setSlotsForExtract(3);
    }

    @Override
    public void update() {
        super.update();
        if (!world.isRemote) {
            processInputSlot();
            processOutputSlot();
        }
    }

    private void checkAndSync() {
        boolean itemChanged = !ItemStack.areItemStacksEqual(storedItem, lastStoredItem);
        if (itemChanged) {
            if (!world.isRemote) {
                RegistryNetwork.network.sendToAllAround(new PacketUpdateItemStorage(this.pos, this.storedItem), packetTargetPoint);
                lastStoredItem = storedItem.isEmpty() ? ItemStack.EMPTY : storedItem.copy();
            }
        }
    }

    @Override
    public void onLoad() {
        this.world.getBlockState(this.pos);
        if (!this.world.isRemote) {
            this.packetTargetPoint = new NetworkRegistry.TargetPoint(this.world.provider.getDimension(), this.pos.getX(), this.pos.getY(), this.pos.getZ(),64);
        }
    }

    public void setDestroyedByCreativePlayer(boolean flag) {
        this.destroyedByCreativePlayer = flag;
    }

    public boolean isDestroyedByCreativePlayer() {
        return this.destroyedByCreativePlayer;
    }

    public void processInputSlot() {
        ItemStack inputStack = inventory.get(0);
        if (!inputStack.isEmpty() && canInsertItem(inputStack)) {
            if (storedItem.isEmpty()) {
                storedItem = inputStack.copy();
                storedItem.setCount(1);
                itemStored = inputStack.getCount();
                inventory.set(0, ItemStack.EMPTY);
                checkAndSync();
            } else if (ItemStack.areItemsEqual(storedItem, inputStack) &&
                    ItemStack.areItemStackTagsEqual(storedItem, inputStack)) {
                int spaceAvailable = itemCapacity - itemStored;
                int itemsToTransfer = Math.min(inputStack.getCount(), spaceAvailable);
                if (itemsToTransfer > 0) {
                    itemStored += itemsToTransfer;
                    inputStack.shrink(itemsToTransfer);
                    if (inputStack.getCount() <= 0) {
                        inventory.set(0, ItemStack.EMPTY);
                    }
                }
            }
        }
    }

    private void processOutputSlot() {
        ItemStack outputStack = inventory.get(3);
        if (outputStack.isEmpty() && !storedItem.isEmpty() && itemStored > 0) {
            int itemsToExtract = Math.min(itemStored, storedItem.getMaxStackSize());
            ItemStack extractedStack = storedItem.copy();
            extractedStack.setCount(itemsToExtract);
            inventory.set(3, extractedStack);
            itemStored -= itemsToExtract;
            if (itemStored <= 0) {
                storedItem = ItemStack.EMPTY;
            }
            checkAndSync();
        } else if (!outputStack.isEmpty() && !storedItem.isEmpty() &&
                ItemStack.areItemsEqual(outputStack, storedItem) &&
                ItemStack.areItemStackTagsEqual(outputStack, storedItem) &&
                itemStored > 0) {
            int spaceAvailable = outputStack.getMaxStackSize() - outputStack.getCount();
            int itemsToExtract = Math.min(itemStored, spaceAvailable);
            if (itemsToExtract > 0) {
                outputStack.grow(itemsToExtract);
                itemStored -= itemsToExtract;
                if (itemStored <= 0) {
                    storedItem = ItemStack.EMPTY;
                }
            }
        }
    }

    private boolean canInsertItem(ItemStack stack) {
        if (stack.isEmpty()) return false;
        ItemStack outputStack = inventory.get(3);
        if (!outputStack.isEmpty()) {
            return ItemStack.areItemsEqual(outputStack, stack) &&
                    ItemStack.areItemStackTagsEqual(outputStack, stack);
        }
        if (storedItem.isEmpty()) {
            return true;
        } else {
            return ItemStack.areItemsEqual(storedItem, stack) &&
                    ItemStack.areItemStackTagsEqual(storedItem, stack) &&
                    itemStored < itemCapacity;
        }
    }

    @Override
    public @Nonnull NBTTagCompound writeToNBT(@Nonnull NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("itemStored", itemStored);
        nbt.setInteger("itemCapacity", itemCapacity);
        if (!storedItem.isEmpty()) {
            NBTTagCompound storedTag = new NBTTagCompound();
            storedItem.writeToNBT(storedTag);
            nbt.setTag("storedItem", storedTag);
        }
        return nbt;
    }

    @Override
    public void readFromNBT(@Nonnull NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        itemStored = nbt.getInteger("itemStored");
        itemCapacity = nbt.getInteger("itemCapacity");
        if (nbt.hasKey("storedItem")) {
            NBTTagCompound storedTag = nbt.getCompoundTag("storedItem");
            storedItem = new ItemStack(storedTag);
        } else {
            storedItem = ItemStack.EMPTY;
        }
    }

    @Override
    public void getSyncData(Map<String, Object> data) {
        data.put("itemStored", itemStored);
        data.put("itemCapacity", itemCapacity);
    }

    @Override
    public void setSyncData(Map<String, Object> data) {
        if (data.containsKey("itemStored")) {
            this.itemStored = (int) data.get("itemStored");
        }
        if (data.containsKey("itemCapacity")) {
            this.itemCapacity = (int) data.get("itemCapacity");
        }
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(new InvWrapper(this) {
                @Nonnull
                @Override
                public ItemStack getStackInSlot(int slot) {
                    if (slot == 0) {
                        return super.getStackInSlot(0);
                    }
                    else if (slot == 1) {
                        return getStoredItemType();
                    }
                    else if (slot == 2) {
                        return super.getStackInSlot(3);
                    }
                    return ItemStack.EMPTY;
                }

                @Override
                public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
                    if (slot != 0) {
                        return stack;
                    }
                    if (!canInsertItem(stack)) {
                        return stack;
                    }
                    return super.insertItem(slot, stack, simulate);
                }

                @Nonnull
                @Override
                public ItemStack extractItem(int slot, int amount, boolean simulate) {
                    if (slot == 1) {
                        if (storedItem.isEmpty() || itemStored <= 0) {
                            return ItemStack.EMPTY;
                        }
                        int itemsToExtract = Math.min(amount, itemStored);
                        itemsToExtract = Math.min(itemsToExtract, storedItem.getMaxStackSize());
                        if (itemsToExtract <= 0) {
                            return ItemStack.EMPTY;
                        }
                        ItemStack result = storedItem.copy();
                        result.setCount(itemsToExtract);
                        if (!simulate) {
                            itemStored -= itemsToExtract;
                            if (itemStored <= 0) {
                                storedItem = ItemStack.EMPTY;
                            }
                            checkAndSync();
                            //UpdateGUI-Use-Capability
                            world.notifyBlockUpdate(pos, blockType.getDefaultState(), blockType.getDefaultState(),3);
                        }
                        return result;
                    } else if (slot == 2) {
                        return super.extractItem(3, amount, simulate);
                    }
                    return ItemStack.EMPTY;
                }
                @Override
                public int getSlotLimit(int slot) {
                    if (slot == 1) {
                        return itemCapacity;
                    }
                    return super.getSlotLimit(slot);
                }
                @Override
                public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                    if (slot == 0) {
                        return canInsertItem(stack);
                    }
                    return false;
                }
            });
        }
        return super.getCapability(capability, facing);
    }

    @Nonnull
    public ItemStack getStoredItemType() {
        if (!storedItem.isEmpty()) {
            ItemStack copy = storedItem.copy();
            copy.setCount(itemStored);
            return copy;
        }
        return ItemStack.EMPTY;
    }

    @Override
    public @Nonnull String getGuiID() {
        return String.valueOf(RegistryGui.GUI_ITEM_STORAGE);
    }
}