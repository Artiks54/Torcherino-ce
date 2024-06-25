package com.ariks.torcherino.Block.ParticleCollector;

import com.ariks.torcherino.Block.TileExampleContainer;
import com.ariks.torcherino.Register.RegistryGui;
import com.ariks.torcherino.Register.RegistryItems;
import com.ariks.torcherino.util.Config;
import com.ariks.torcherino.util.InvWrapperRestricted;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.items.CapabilityItemHandler;
import org.jetbrains.annotations.NotNull;
import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Collections;

public class TileParticleCollector extends TileExampleContainer implements ITickable, IInventory, ISidedInventory {
    protected NonNullList<ItemStack> inventory;
    private int amount = 1;
    public int percent;
    private int MaxProgress = Config.RequiredGeneratorParticle;
    private int addProgress,NeedNext,progress;
    private final InvWrapperRestricted invHandler;
    public int level = 1;
    public int TotalGeneratedUp;
    public TileParticleCollector(){
        invHandler = new InvWrapperRestricted(this);
        inventory = NonNullList.withSize(2,ItemStack.EMPTY);
        invHandler.setSlotsExtract(Collections.singletonList(0));
    }
    @Override
    public void update() {
        if (!world.isRemote) {
            this.CheckUpgrade();
            this.SystemLevelUp();
            this.GenerateMyInventory();
            this.UpdateTile();
        }
    }
    private void CheckUpgrade(){
        if (inventory.get(1).getItem() == RegistryItems.upgrade_kit6){
            amount = 64;
        } else if (inventory.get(1).getItem() == RegistryItems.upgrade_kit5){
            amount = 32;
        } else if (inventory.get(1).getItem() == RegistryItems.upgrade_kit4){
            amount = 16;
        } else if (inventory.get(1).getItem() == RegistryItems.upgrade_kit3){
            amount = 8;
        } else if (inventory.get(1).getItem() == RegistryItems.upgrade_kit2){
            amount = 4;
        } else if (inventory.get(1).getItem() == RegistryItems.upgrade_kit1){
            amount = 2;
        } else if (inventory.get(1).isEmpty()){
            amount = 1;
        }
    }
    private void SystemLevelUp() {
        if (level == 5) {
            addProgress = 5;
            NeedNext = 0;
            TotalGeneratedUp = 0;
        }
        else if (level == 4) {
            addProgress = 4;
            NeedNext = 2000;
            this.CheckLevel();
        }
        else if (level == 3) {
            addProgress = 3;
            NeedNext = 1000;
            this.CheckLevel();
        }
        else if (level == 2) {
            addProgress = 2;
            NeedNext = 500;
            this.CheckLevel();
        }
        else if (level == 1) {
            addProgress = 1;
            NeedNext = 250;
            this.CheckLevel();
        }
    }
    private void CheckLevel(){
        if (TotalGeneratedUp == NeedNext) {
            level++;
            TotalGeneratedUp = 0;
        }
    }
    private void GenerateMyInventory(){
        int slotGenerated = 0;
        if (inventory.get(slotGenerated).isEmpty() || inventory.get(slotGenerated).getCount() < 64) {
            progress += addProgress;
            percent = (progress * 100) / MaxProgress;
        }
        if (progress >= MaxProgress) {
            if (inventory.get(slotGenerated).isEmpty()) {
                inventory.set(slotGenerated, new ItemStack(RegistryItems.time_particle, amount));
            } else if (inventory.get(slotGenerated).getCount() < 64) {
                int availableSpace = 64 - inventory.get(slotGenerated).getCount();
                int toAdd = Math.min(amount, availableSpace);
                inventory.get(slotGenerated).grow(toAdd);
            }
            if(level < 5) {
                TotalGeneratedUp++;
            }
            progress = 0;
        }
    }
    @Override
    public @NotNull NBTTagCompound writeToNBT(@NotNull NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("progress", progress);
        nbt.setInteger("generated",TotalGeneratedUp);
        nbt.setInteger("amount",amount);
        nbt.setInteger("level",level);
        nbt.setInteger("up",NeedNext);
        nbt.setTag("inventory", ItemStackHelper.saveAllItems(new NBTTagCompound(), inventory));
        return nbt;
    }
    @Override
    public void readFromNBT(@NotNull NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        progress = nbt.getInteger("progress");
        amount = nbt.getInteger("amount");
        TotalGeneratedUp = nbt.getInteger("generated");
        level = nbt.getInteger("level");
        NeedNext = nbt.getInteger("up");
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
        if (id == 3) {
            return this.TotalGeneratedUp;
        }
        if (id == 4) {
            return this.NeedNext;
        }
        if (id == 5) {
            return this.level;
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
        if (id == 3) {
            this.TotalGeneratedUp = value;
        }
        if (id == 4) {
            this.NeedNext = value;
        }
        if (id == 5) {
            this.level = value;
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
    public boolean isItemValidForSlot(int i, ItemStack itemStack) {
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
    public String getGuiID() {
        return String.valueOf(RegistryGui.GUI_PARTICLE_COLLECTOR);
    }
    @Override
    public int[] getSlotsForFace(EnumFacing enumFacing) {
        return new int[2];
    }
    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return invHandler.canInsert(index);
    }
    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        return invHandler.canExtract(index);
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