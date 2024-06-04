package com.ariks.torcherino.Block.ParticleCollector;

import com.ariks.torcherino.Block.TileExampleContainer;
import com.ariks.torcherino.Register.RegistryGui;
import com.ariks.torcherino.Register.RegistryItems;
import com.ariks.torcherino.util.Config;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import org.jetbrains.annotations.NotNull;
import javax.annotation.Nonnull;

public class TileParticleCollector extends TileExampleContainer implements ITickable, IInventory {
    private final NonNullList<ItemStack> inventory = NonNullList.withSize(1, ItemStack.EMPTY);
    public int amount,percent;
    private int progress;
    private int MaxProgress = Config.RequiredGeneratorParticle;
    @Override
    public void update() {
        if (!world.isRemote) {
            this.GenerateMyInventory();
        }
    }
    private void GenerateMyInventory(){
        int slotGenerated = 0;
        if (inventory.get(slotGenerated).isEmpty() || inventory.get(slotGenerated).getCount() < 64) {
            progress ++;
            percent = (progress * 100) / MaxProgress;
            this.UpdateTile();
        }
        if (progress >= MaxProgress) {
            if (inventory.get(slotGenerated).isEmpty()) {
                inventory.set(slotGenerated, new ItemStack(RegistryItems.time_particle, amount));
            } else if (inventory.get(slotGenerated).getCount() < 64) {
                int availableSpace = 64 - inventory.get(slotGenerated).getCount();
                int toAdd = Math.min(amount, availableSpace);
                inventory.get(slotGenerated).grow(toAdd);
            }
            progress = 0;
        }
    }

    @Override
    public @NotNull NBTTagCompound writeToNBT(@NotNull NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("progress", progress);
        nbt.setInteger("amount",amount);
        nbt.setTag("inventory", ItemStackHelper.saveAllItems(new NBTTagCompound(), inventory));
        return nbt;
    }
    @Override
    public void readFromNBT(@NotNull NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        progress = nbt.getInteger("progress");
        amount = nbt.getInteger("amount");
        NBTTagCompound inventoryTag = nbt.getCompoundTag("inventory");
        ItemStackHelper.loadAllItems(inventoryTag, inventory);
    }
    @Override
    public int getValue(int id) {
        if (id == 1) {
            return this.progress;
        }
        if (id == 2) {
            return this.MaxProgress;
        }
        return id;
    }
    @Override
    public void setValue(int id, int value) {
        if (id == 1) {
            this.progress = value;
        }
        if (id == 2) {
            this.MaxProgress = value;
        }
    }
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
    public int getInventoryStackLimit() {
        return 64;
    }
    @Override
    public boolean isUsableByPlayer(@Nonnull EntityPlayer player) {
        return !isInvalid() && player.getDistanceSq(pos.add(0.5, 0.5, 0.5)) <= 64;
    }
    @Override
    public void openInventory(@Nonnull EntityPlayer player) {}
    @Override
    public void closeInventory(@Nonnull EntityPlayer player) {}
    @Override
    public boolean isItemValidForSlot(int index, @Nonnull ItemStack stack) {
        return false;
    }
    @Override
    public int getField(int i) {return 0;}
    @Override public void setField(int i, int i1) {}
    @Override
    public int getFieldCount() {return 0;}
    @Override
    public void clear() {
        this.inventory.clear();
    }
    @Override
    public @NotNull String getName() {
        return "TileParticle";
    }
    @Nonnull
    @Override
    public ITextComponent getDisplayName() {
        return new TextComponentString(getName());
    }
    @Override
    public @NotNull Container createContainer(@NotNull InventoryPlayer playerInventory, @NotNull EntityPlayer playerIn) {
        return new ContainerParticleCollector(playerInventory,this, playerIn);
    }
    @Override
    public @NotNull String getGuiID() {
        return String.valueOf(RegistryGui.GUI_PARTICLE_COLLECTOR);
    }
}