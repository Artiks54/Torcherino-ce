package com.artiks.torcherinoCe.Block.Core.Slots;

import com.artiks.torcherinoCe.Block.Energy.CobbleGenerator.TileCobbleGenerator;
import com.artiks.torcherinoCe.Register.RegistryItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import java.util.Objects;

public class SlotUpgradeGen extends Slot {
    public SlotUpgradeGen(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    private ItemStack getGen(){
        if(getStack().getItem() == RegistryItems.upgrade_gravel){
            return new ItemStack(Objects.requireNonNull(Blocks.GRAVEL),1920);
        }
        if(getStack().getItem() == RegistryItems.upgrade_sand) {
            return new ItemStack(Objects.requireNonNull(Blocks.SAND),1920);
        }
        return new ItemStack(Objects.requireNonNull(Blocks.COBBLESTONE),1920);
    }

    private int getEnergyPerTick(){
        if(getStack().getItem() == RegistryItems.upgrade_gravel){
            return 12000;
        }
        if(getStack().getItem() == RegistryItems.upgrade_sand) {
            return 12000;
        }
        return 4500;
    }

    @Override
    public @NotNull ItemStack onTake(@NotNull EntityPlayer thePlayer, @NotNull ItemStack stack) {
        ((TileCobbleGenerator)inventory).setGeneration(getGen());
        ((TileCobbleGenerator)inventory).setEnergyPerTick(getEnergyPerTick());
        return super.onTake(thePlayer, stack);
    }

    @Override
    public void onSlotChanged() {
        super.onSlotChanged();
        ((TileCobbleGenerator)inventory).setGeneration(getGen());
        ((TileCobbleGenerator)inventory).setEnergyPerTick(getEnergyPerTick());
    }

    @Override
    public boolean isItemValid(@NotNull ItemStack stack) {
        if(stack.getItem() == RegistryItems.upgrade_gravel){
            return true;
        }
        return stack.getItem() == RegistryItems.upgrade_sand;
    }

    @Override
    public int getItemStackLimit(@NotNull ItemStack stack) {
        return 1;
    }

    @Override
    public int getSlotStackLimit() {
        return 1;
    }
}
