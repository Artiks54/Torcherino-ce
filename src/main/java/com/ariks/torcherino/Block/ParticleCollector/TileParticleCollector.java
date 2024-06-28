package com.ariks.torcherino.Block.ParticleCollector;

import com.ariks.torcherino.Block.TileExampleInventory;
import com.ariks.torcherino.Items.ItemUpgrade;
import com.ariks.torcherino.Register.RegistryGui;
import com.ariks.torcherino.Register.RegistryItems;
import com.ariks.torcherino.util.Config;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.entity.player.EntityPlayer;
import org.jetbrains.annotations.NotNull;

public class TileParticleCollector extends TileExampleInventory implements ITickable {
    private int amount = 1;
    public int percent;
    private int MaxProgress = Config.RequiredGeneratorParticle;
    private int addProgress,NeedNext,progress;
    public int level = 1;
    public int TotalGeneratedUp;
    public TileParticleCollector(){
        super(2);
        this.setSlotsForInsert(1);
        this.setSlotsForExtract(0);
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
    public boolean isItemValidForSlot(int index, ItemStack itemStack) {
        if(index == 1 && inventory.get(1).isEmpty() && itemStack.getItem() instanceof ItemUpgrade){
            return true;
        }
        return false;
    }
    @Override
    public @NotNull Container createContainer(@NotNull InventoryPlayer playerInventory, @NotNull EntityPlayer playerIn) {
        return new ContainerParticleCollector(playerInventory,this, playerIn);
    }
    @Override
    public String getGuiID() {
        return String.valueOf(RegistryGui.GUI_PARTICLE_COLLECTOR);
    }
}