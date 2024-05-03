package com.ariks.torcherino.Block.ParticleCollector;

import com.ariks.torcherino.Register.RegistryGui;
import com.ariks.torcherino.Register.RegistryItems;
import com.ariks.torcherino.util.Config;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.ITickable;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import org.jetbrains.annotations.NotNull;
import javax.annotation.Nonnull;

public class TileParticleCollector extends TileEntityLockable implements ITickable {
    private final ItemStackHandler inventory = new ItemStackHandler(1) {@Override protected void onContentsChanged(int slot) {markDirty();}};
    public int amount;
    private int progress;
    private int MaxProgress = Config.RequiredGeneratorParticle;
    public int percent;

    @Override
    public void update() {
        if (!world.isRemote) {
            if (inventory.getStackInSlot(0).isEmpty() || inventory.getStackInSlot(0).getCount() < 64) {
                progress++;
            }
            if (inventory.getStackInSlot(0).getCount() == 64) {
                progress = 0;
            }
            if (progress >= MaxProgress) {
                if (inventory.getStackInSlot(0).isEmpty()) {
                    inventory.insertItem(0, new ItemStack(RegistryItems.time_particle, amount), false);
                } else if (inventory.getStackInSlot(0).getItem() == RegistryItems.time_particle && inventory.getStackInSlot(0).getCount() < 64) {
                    int availableSpace = 64 - inventory.getStackInSlot(0).getCount();
                    int toAdd = Math.min(amount, availableSpace);
                    inventory.getStackInSlot(0).grow(toAdd);
                }
                progress = 0;
            }
            percent = (progress * 100) / MaxProgress;
        }
    }
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setTag("inventory", inventory.serializeNBT());
        nbt.setInteger("progress", progress);
        nbt.setInteger("amount",amount);
        return nbt;
    }
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        inventory.deserializeNBT(nbt.getCompoundTag("inventory"));
        progress = nbt.getInteger("progress");
        amount = nbt.getInteger("amount");
    }
    @Override
    public int getSizeInventory() {
        return 1;
    }
    @Override
    public boolean isEmpty() {
        return false;
    }
    @Nonnull
    @Override
    public ItemStack getStackInSlot(int index) {
        return inventory.getStackInSlot(index);
    }
    @Nonnull
    @Override
    public ItemStack decrStackSize(int index, int count) {
        return inventory.extractItem(index, count, false);
    }
    @Nonnull
    @Override
    public ItemStack removeStackFromSlot(int index) {
        return inventory.extractItem(index, inventory.getSlotLimit(index), false);
    }
    @Override
    public void setInventorySlotContents(int index, @Nonnull ItemStack stack) {
        inventory.setStackInSlot(index, stack);
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
    public void openInventory(@Nonnull EntityPlayer player) {
    }
    @Override
    public void closeInventory(@Nonnull EntityPlayer player) {
    }
    @Override
    public boolean isItemValidForSlot(int index, @Nonnull ItemStack stack) {
        return false;
    }
    public int getField(int id)
    {
        if (id == 1) {
            return this.progress;
        }
        if (id == 2) {
            return this.MaxProgress;
        }
        return id;
    }
    public void setField(int id, int value)
    {
        if (id == 1) {
            this.progress = value;
        }
        if (id == 2) {
            this.MaxProgress = value;
        }
    }
    @Override
    public int getFieldCount() {
        return 2;
    }
    @Override
    public void clear() {
        this.inventory.setSize(0);
    }
    @Nonnull
    @Override
    public String getName() {
        return "Tile_Particle_Collector";
    }
    @Override
    public boolean hasCustomName() {
        return false;
    }
    @Nonnull
    @Override
    public ITextComponent getDisplayName() {
        return new TextComponentString(getName());
    }
    @Override
    public @NotNull Container createContainer(@NotNull InventoryPlayer inventoryPlayer, @NotNull EntityPlayer entityPlayer) {
        return new ContainerParticleCollector(inventoryPlayer,this,entityPlayer);
    }
    @Override
    public @NotNull String getGuiID() {
        return String.valueOf(RegistryGui.GUI_PARTICLE_COLLECTOR);
    }
}